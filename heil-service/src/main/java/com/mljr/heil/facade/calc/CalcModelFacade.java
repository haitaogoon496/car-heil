package com.mljr.heil.facade.calc;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.annotation.LogMonitor;
import com.mljr.heil.common.consts.HeilCode;
import com.mljr.heil.common.enums.DictionaryConstant;
import com.mljr.heil.common.enums.FormulaTypeEnum;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.common.exception.BizException;
import com.mljr.heil.common.util.ValidateUtils;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.CalcModel;
import com.mljr.heil.entity.CalcModelParams;
import com.mljr.heil.entity.Product;
import com.mljr.heil.form.CalcModelForm;
import com.mljr.heil.form.CalcModelParamsForm;
import com.mljr.heil.form.ProductForm;
import com.mljr.heil.service.calc.CalcModelParamsService;
import com.mljr.heil.service.calc.CalcModelService;
import com.mljr.heil.service.product.ProductService;
import com.mljr.heil.vo.calc.CalcModelParamsVo;
import com.mljr.heil.vo.calc.CalcModelVo;
import com.mljr.heil.vo.product.SimpleProductVo;
import com.mljr.heil.vo.common.SyArgControlVo;
import com.mljr.heil.voconvertor.calc.CalcModelParamsVoConvertor;
import com.mljr.heil.voconvertor.calc.CalcModelVoConvertor;
import com.mljr.heil.voconvertor.common.SimpleProductVoConvertor;
import com.mljr.util.StringTools;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.*;

/**
 * @description: 产品公式设置 Facade
 * @Date : 2018/2/27 下午6:24
 * @Author : liht
 */
@Component
public class CalcModelFacade implements BaseFacade<CalcModelVo,CalcModel,Integer,CalcModelForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.CALC_MODEL.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.PD_CALC_MODEL;

    @Autowired
    private CalcModelService calcModelService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private ProductService productService;
    @Autowired
    private CalcModelParamsService calcModelParamsService;

    private Map<String, String> urlMap = new HashMap<>();

    @PostConstruct
    public void initData() {
        urlMap.put("gpsRule","/gpsRule/loadRecords");
        urlMap.put("rateRule","/rateRule/loadRecords");
        urlMap.put("serFinRule","/serFinRule/loadRecords");
        urlMap.put("secondRule","/yanbaoRule/loadRecords");
        urlMap.put("thirdRule","/yanbaoRule/loadRecords");
        urlMap.put("accountRule","/accountRule/loadRecords");
        urlMap.put("extendSafeRule","/extendSafeRule/loadRecords");
        urlMap.put("pdFeeRule","/pdFeeRule/loadRecords");
    }

    @LogMonitor("计算公式配置")
    @Override
    public Result<PageVO<CalcModelVo>> loadRecords(PageForm<CalcModelForm> form) {
        List<CalcModelVo> voList = Collections.emptyList();
        int count = 0;
        try {
            final String symbol = "/";
            final String defaultView = "--";
            List<CalcModel> queryList = this.calcModelService.queryByPage(form);
            if (CollectionUtils.isEmpty(queryList)) {
                return Result.failInEmptyRecord(null);
            }
            count = this.calcModelService.queryCount(form);
            voList = new CalcModelVoConvertor().convertList(queryList);
            voList.forEach(model -> {
                StringBuffer sb = new StringBuffer();
                sb.append(StringTools.valueOf(model.getProductTypeId(),defaultView)).append(symbol);
                sb.append(StringTools.valueOf(model.getProductId(),defaultView)).append(symbol);
                sb.append(StringTools.valueOf(model.getRuleId(),defaultView));
                model.setUnionDesc(sb.toString());
                model.setFormulaType(model.isDefaultFormulaType() ? FormulaTypeEnum.DEFAULT.getName() : FormulaTypeEnum.CUSTOM.getName());
            });
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    @LogMonitor("计算公式配置")
    @Override
    public Result<CalcModelVo> queryRecord(Integer primaryKey) {
        CalcModelVo vo = null;
        try {
            CalcModel record = this.calcModelService.queryRecord(primaryKey);
            if(null == record ){
                return Result.failInEmptyRecord(null);
            }
            vo = new CalcModelVoConvertor().convert(record);
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @Override
    @Transactional(rollbackFor = {BizException.class,Exception.class})
    public Result<String> saveRecord(CalcModel record) {
        int zero = 0;
        Byte defaultValue = new Byte("0");
        return commonComponent.saveRecord(record,authModelEnum,LOG_TITLE,calcModelService,entity -> {
            CalcModel calcModel = (CalcModel)entity;
            Date current = TimeTools.createNowTime();
            record.setUpdateTime(current);
            record.setProductTypeId(Optional.ofNullable(record.getProductTypeId()).orElse(defaultValue));
            record.setProductId(Optional.ofNullable(record.getProductId()).orElse(zero));
            record.setRuleType(Optional.ofNullable(record.getRuleType()).orElse(defaultValue));
            record.setRuleId(Optional.ofNullable(record.getRuleId()).orElse(zero));
            record.setFormulaCode(DictionaryConstant.FormulaEnum.getByVal(record.getFormulaName()));
            if(calcModel.isInsert()){
                calcModel.setCreateTime(current);
            }
        });
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,calcModelService,(PK) ->
                saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, MessageFormat.format("删除primaryKey={0}",PK))
        );
    }
    @LogMonitor("计算公式配置")
    public Result<String> modifyStatus(CalcModel record) {
        ValidateUtils.notNull(record.getId(),HeilCode.E_400,"参数主键不能为空");
        ValidateUtils.notEquals(record.getId(), 0, HeilCode.E_400, "参数主键错误");

        this.calcModelService.updateRecord(record);
        this.saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("状态更新param={0}",JSON.toJSON(record)));
        return Result.suc();
    }

    /**
     * 保存操作日志
     * @param authTypeEnum
     * @param authDetail
     */
    public void saveLog(UserOperateLogConstant.AuthTypeEnum authTypeEnum,String authDetail){
        this.userOperateLogComponent.log(log -> {
            log.setAuthDetail(authModelEnum.getName()+authDetail);
            log.setAuthModel(authModelEnum);
            log.setAuthType(authTypeEnum);
        });
    }

    /**
     * 查询产品列表
     * @param form
     * @return
     */
    public Result<PageVO<SimpleProductVo>> querySimpleProductRecords(PageForm<ProductForm> form) {
        LOGGER.info("querySimpleProductRecords - 参数:{}",JSON.toJSON(form));
        ValidateUtils.notNull(form.getForm(),HeilCode.E_400,"产品类型不能为空");
        ValidateUtils.notNull(form.getForm().getType(),HeilCode.E_400,"产品类型不能为空");
        //查出所有启用的产品
        form.getForm().setStatus(DictionaryConstant.ProductTypeEnum.ENABLE.getIndex());
        form.setPaging(false);
        List<Product> products = productService.queryByPage(form);
        if (CollectionUtils.isEmpty(products)) {
            return Result.fail(HeilCode.E_400, "没有可用的产品");
        }
        int count = productService.queryCount(form);
        List<SimpleProductVo> productVos = new SimpleProductVoConvertor().convertList(products);
        return Result.suc(new PageVO<SimpleProductVo>(form.getDraw(), count, productVos));
    }

    /**
     * 根据规则类型获取其对应的请求url
     * @param ruleType
     * @return
     */
    public Result<String> queryRuleTypeUrl(String ruleType) {
        LOGGER.info("{} - queryRuleTypeUrl - 参数:{}", LOG_TITLE, ruleType);
        ValidateUtils.notNull(ruleType, HeilCode.E_400, "参数ruleType不能为空");
        String url = urlMap.get(ruleType);
        ValidateUtils.notNull(url, HeilCode.E_400, "参数没有对应url,请核实后操作");
        LOGGER.info("url值：{}",url);
        return Result.suc(url);
    }

    /**
     * 通过公式名称对应的code获取其对应的费用规则
     * @param formulaCode
     * @return
     */
    public Result<SyArgControlVo> getFeeRuleByFormulaCode(String formulaCode) {
        LOGGER.info("{} - getFeeRuleByFormulaCode 参数-formulaCode:{}", LOG_TITLE, formulaCode);
        //通过公式名称获取对应的规则
        DictionaryConstant.RuleTypeEnum ruleTypeEnum = DictionaryConstant.FormulaEnum.getByName(formulaCode);
        LOGGER.info("{} - 该{}公式对应的费用规则：{}", LOG_TITLE, formulaCode, ruleTypeEnum == null ? "" : ruleTypeEnum.getDesc());
        if (ruleTypeEnum == null) {
            return Result.failInEmptyRecord(null);
        }
        SyArgControlVo syArgControlVo = new SyArgControlVo();
        syArgControlVo.setId(ruleTypeEnum.getIndex());
        syArgControlVo.setCode(String.valueOf(ruleTypeEnum.getIndex()));
        syArgControlVo.setName(String.valueOf(ruleTypeEnum.getName()));
        syArgControlVo.setValue(String.valueOf(ruleTypeEnum.getDesc()));
        return Result.suc(syArgControlVo);
    }

    /**
     * 根据公式名称查询对应的参数
     * @param form
     * @return List<E>
     */
    public Result<List<CalcModelParamsVo>> getParamByFormulaCode(PageForm<CalcModelParamsForm> form) {
        LOGGER.info("{} - 根据公式名称获取对应参数 - 请求form:{}", LOG_TITLE, JSON.toJSON(form));
        List<CalcModelParams> calcModelParamses = calcModelParamsService.getParamByFormulaCode(form);
        LOGGER.info("{} - 根据公式名称获取对应参数 - 获取数据结果:{}", JSON.toJSON(calcModelParamses));
        if (calcModelParamses == null) {
            return Result.failInEmptyRecord(null);
        }
        List<CalcModelParamsVo> calcModelParamsVos = Collections.emptyList();
        calcModelParamsVos = new CalcModelParamsVoConvertor().convertList(calcModelParamses);

        return Result.suc(calcModelParamsVos);
    }
}
