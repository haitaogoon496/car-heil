package com.mljr.heil.facade.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.ProductConstant;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.annotation.LogMonitor;
import com.mljr.common.TwoTuple;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.heil.common.exception.BizException;
import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.EntityComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.Product;
import com.mljr.heil.entity.ProductProps;
import com.mljr.heil.entity.ProductType;
import com.mljr.heil.form.ProductForm;
import com.mljr.heil.form.ProductPropsForm;
import com.mljr.heil.handle.ProductHandle;
import com.mljr.heil.service.product.ProductPropsService;
import com.mljr.heil.service.product.ProductService;
import com.mljr.heil.service.product.ProductTypeService;
import com.mljr.heil.vo.common.ModifyStatusVo;
import com.mljr.heil.vo.product.ProductPropVo;
import com.mljr.heil.vo.product.ProductVo;
import com.mljr.heil.voconvertor.product.ProductVoConvertor;
import com.mljr.redis.service.RedisUtil;
import com.mljr.util.CollectionsTools;
import com.mljr.util.TimeTools;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @description: 车贷产品 Facade
 * @Date : 2018/2/27 下午6:24
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Component
public class ProductFacade implements BaseFacade<ProductVo,Product,Integer,ProductForm>,ProductHandle {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.PRODUCT.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.CAR_PRODUCT_MANAGE;
    private static final String REDIS_PRODUCT_INCR = "redisProductIncr";

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductPropsService productPropsService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private EntityComponent entityComponent;
    @Autowired
    private CommonComponent commonComponent;

    @Value("${spring.profiles.active}")
    private String env;
    @Autowired
    private RedisUtil redisUtil;

    //@LogMonitor("车贷产品管理")
    @Override
    public Result<PageVO<ProductVo>> loadRecords(PageForm<ProductForm> form) {
        List<ProductVo> voList = Collections.emptyList();
        int count = 0;
        try {
            List<Product> queryList = this.productService.queryByPage(form);
            if (CollectionUtils.isEmpty(queryList)) {
                return Result.failInEmptyRecord(null);
            }
            count = this.productService.queryCount(form);
            voList = new ProductVoConvertor().convertList(queryList);
            commonComponent.bindProductProps(voList);
            commonComponent.bindTags(TwoTuple.newInstance(TagConstant.BuzTypeEnum.PRODUCT.getIndex(),voList),
                    rule -> Integer.valueOf(rule.getPId()),(rule, tags) -> rule.setTags(tags));
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    @LogMonitor("车贷产品管理")
    @Override
    public Result<ProductVo> queryRecord(Integer primaryKey) {
       ProductVo vo = null;
        try {
            Product record = this.productService.queryRecord(primaryKey);
            List<ProductProps> props = this.productPropsService.queryList(new ProductPropsForm(primaryKey));
            if(null != record ){
                vo = new ProductVoConvertor().convert(record);
                JSONObject json = new JSONObject();
                if(CollectionsTools.isNotEmpty(props)){
                    props.forEach(each -> json.put(each.getPropName(),each.getPropValue()));
                    ProductPropVo propVo = JSONObject.parseObject(json.toJSONString(),ProductPropVo.class);
                    vo.setPropVo(propVo);
                }
            }else{
                return Result.failInEmptyRecord(vo);
            }

        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @Override
    @Transactional(rollbackFor = {BizException.class,Exception.class})
    public Result<String> saveRecord(Product record) {
        LOGGER.info("{} - saveRecord 参数:{}",LOG_TITLE,JSON.toJSON(record));
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(record);
        if (CollectionsTools.isNotEmpty(violations)) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM.getIndex(),violations.get(0).getMessage());
        }
        Date current = TimeTools.createNowTime();
        Integer id = record.getpId();
        record.setLastUpdate(current);
        record.setVer(1);
        entityComponent.setCreateInfo(record);

        if(null == id || id.equals(0) ){
            if(StringUtils.isEmpty(record.getUserName())){
                record.setUserName("admin");
            }
            // 产品编码，生成规则：Z/Q+分类编码+系统日期yyMMdd+3位自增
            ProductType productType = productTypeService.queryRecord(record.getType());
            if(productType == null) {
                return Result.fail(RemoteEnum.ILLEGAL_ARGUMENTS.getIndex(), "查询不到产品分类");
            }
            String sysIdn = env.contains("zy") ? "Z" : "Q";
            String productCodePre = new StringBuffer(sysIdn).append(productType.getpClassCode()).append(TimeTools.format(current, "yyMMdd")).toString();
            String redisKey = redisUtil.getKeyWithSystemCode(REDIS_PRODUCT_INCR + productCodePre);
            redisUtil.incr(redisKey.getBytes(),0);
            long currentValue = redisUtil.getIncrValue(redisKey);
            record.setProductCoe(String.format("%1$s%2$03d", productCodePre, currentValue));
            if(StringUtils.isEmpty(record.getDesp())) {
                record.setDesp(record.getpName());
            }
            record.setYear(TimeTools.getYear(TimeTools.createNowTime()));
            record.setCreateTime(current);
            record.setStatus(ProductConstant.ProductStatusEnum.PAUSE.getIndex());
            productService.insertRecord(record);
            this.saveLog(UserOperateLogConstant.AuthTypeEnum.CREATE, MessageFormat.format("新增record={0}",JSON.toJSON(record)));
        }else{
            ProductPropsForm productPropsForm = new ProductPropsForm();
            productPropsForm.setProductId(record.getpId());
            productPropsService.delByForm(productPropsForm);
            productService.updateRecord(record);
            this.saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("修改record={0}",JSON.toJSON(record)));
        }
        List<ProductProps> propList = record.getPropList();
        if(CollectionsTools.isNotEmpty(propList)){
            propList.forEach(prop -> {
                prop.setpId(record.getpId());
                productPropsService.insertRecord(prop);
            });
        }

        return Result.suc();
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,productService,(PK) ->
                saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, MessageFormat.format("删除primaryKey={0}",PK))
        );
    }

    @Override
    public Result<String> modifyStatus(ModifyStatusVo param) {
        LOGGER.info("产品状态更新 - 参数:{}", JSON.toJSON(param));
        this.productService.modifyStatus(param);
        this.saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("产品状态更新param={0}",JSON.toJSON(param)));
        return Result.suc();
    }

    /**
     * 保存操作日志
     * @param authTypeEnum
     * @param authDetail
     */
    public void saveLog(UserOperateLogConstant.AuthTypeEnum authTypeEnum,String authDetail){
        this.userOperateLogComponent.log(log -> {
            log.setAuthDetail(authDetail);
            log.setAuthModel(authModelEnum);
            log.setAuthType(authTypeEnum);
        });
    }
}
