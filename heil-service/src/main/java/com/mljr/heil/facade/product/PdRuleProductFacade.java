package com.mljr.heil.facade.product;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.heil.enums.TagConstant.BuzTypeEnum;
import com.mljr.annotation.OvalValidator;
import com.mljr.common.ThreeTuple;
import com.mljr.heil.common.consts.HeilCode;
import com.mljr.heil.common.util.ValidateUtils;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.core.querylink.QueryLinkContext;
import com.mljr.heil.core.querylink.QueryLinkHandlerFactory;
import com.mljr.heil.facade.rule.GpsRuleFacade;
import com.mljr.heil.facade.rule.SerFinRuleFacade;
import com.mljr.heil.facade.rule.YanbaoRuleFacade;
import com.mljr.heil.form.PdRuleProductForm;
import com.mljr.heil.service.product.PdRuleProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @description: 车贷产品管理-费用设置
 * @Date : 2018/2/28 16:15
 * @Author : lihaitao
 */
@Component
public class PdRuleProductFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.CAR_PRODUCT_FEE_SET;

    @Autowired
    private PdRuleProductService pdRuleProductService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    GpsRuleFacade gpsRuleFacade;
    @Autowired
    YanbaoRuleFacade yanbaoRuleFacade;
    @Autowired
    SerFinRuleFacade serFinRuleFacade;


    /**
     * 车贷产品管理-费用设置-查询已关联
     * @param form
     * @return
     */
    public Result<PageVO> queryBindRules(PageForm<PdRuleProductForm> form) {
        LOGGER.info("车贷产品管理-费用设置-查询已关联 - queryBindRules 参数:{}",JSON.toJSON(form));
        ValidateUtils.notNull(form, HeilCode.E_400, "productId不能为空");
        ValidateUtils.notNull(form.getForm().getProductId(),HeilCode.E_400,"productId不能为空");
        ValidateUtils.notNull(form.getForm().getClassify(),HeilCode.E_400,"classify不能为空");
        BuzTypeEnum buzTypeEnum = BuzTypeEnum.getByIndex(form.getForm().getClassify());
        ValidateUtils.notNull(buzTypeEnum,HeilCode.E_400,"不支持当前业务类型");
        form.getForm().setStatus(1);
        QueryLinkContext context = QueryLinkContext.builder()
                .yanbaoRuleFacade(yanbaoRuleFacade).commonComponent(commonComponent)
                .pdRuleProductService(pdRuleProductService).serFinRuleFacade(serFinRuleFacade)
                .gpsRuleFacade(gpsRuleFacade)
                .form(form).build();
        QueryLinkHandlerFactory.create(buzTypeEnum).execute(context);
        return Result.suc(PageVO.newInstance(form.getDraw(),context.getCount(),context.getVoList()));
    }

    /**
     * 车贷产品管理-费用设置-查询未关联
     * @param form
     * @return
     */
    public Result<PageVO> queryNoBindRules(PageForm<PdRuleProductForm> form) {
        LOGGER.info("车贷产品管理-费用设置-查询未关联 - queryNoBindRules 参数:{}",JSON.toJSON(form));
        ValidateUtils.notNull(form, HeilCode.E_400, "productId不能为空");
        ValidateUtils.notNull(form.getForm().getProductId(),HeilCode.E_400,"productId不能为空");
        ValidateUtils.notNull(form.getForm().getClassify(),HeilCode.E_400,"classify不能为空");
        BuzTypeEnum buzTypeEnum = BuzTypeEnum.getByIndex(form.getForm().getClassify());
        ValidateUtils.notNull(buzTypeEnum,HeilCode.E_400,"不支持当前业务类型");

        form.getForm().setBindFlag(false);
        return queryBindRules(form);
    }

    /**
     * 删除绑定关系
     * @param form
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @OvalValidator("车贷产品管理-费用设置-删除关联")
    public Result<String> delComponentProps(PdRuleProductForm form){
        LOGGER.info("车贷产品管理-费用设置-删除绑定关系--参数:{}", JSON.toJSON(form));
        ValidateUtils.notEmpty(form.getRuleIdList(), HeilCode.E_400,"请选择要保存的数据");
        ValidateUtils.notNull(form.getClassify(), HeilCode.E_400, "classify不能为空");
        ValidateUtils.notNull(form.getProductId(), HeilCode.E_400, "产品id不能为空");
        BuzTypeEnum buzTypeEnum = BuzTypeEnum.getByIndex(form.getClassify());
        ValidateUtils.notNull(buzTypeEnum,HeilCode.E_400,"不支持当前业务类型");
        saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE,getLogDetail(form));
        int count = pdRuleProductService.deleteByForm(form);
        return Result.suc(String.valueOf(count));
    }

    /**
     * 添加绑定关系
     * @param form
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @OvalValidator("车贷产品管理-费用设置-添加关联")
    public Result<String> addComponentProps(PdRuleProductForm form){
        LOGGER.info("车贷产品管理-费用设置-添加绑定关系--参数:{}", JSON.toJSON(form));
        ValidateUtils.notEmpty(form.getPdRuleProductList(), HeilCode.E_400,"请选择要保存的数据");
        ValidateUtils.notNull(form.getPdRuleProductList().get(0).getClassify(), HeilCode.E_400, "classify不能为空");
        ValidateUtils.notNull(form.getPdRuleProductList().get(0).getProductId(), HeilCode.E_400, "产品id不能为空");
        BuzTypeEnum buzTypeEnum = BuzTypeEnum.getByIndex(form.getPdRuleProductList().get(0).getClassify());
        ValidateUtils.notNull(buzTypeEnum,HeilCode.E_400,"不支持当前业务类型");
        saveLog(UserOperateLogConstant.AuthTypeEnum.CREATE,getLogDetail(form));
        int count = pdRuleProductService.batchInsert(form.getPdRuleProductList());
        return Result.suc(String.valueOf(count));
    }

    /**
     * 从form对象中获取日志详情
     * @param form
     * @return
     */
    private String getLogDetail(PdRuleProductForm form){
        Integer classify = form.getClassify();
        Integer productId = form.getProductId();
        List<Integer> ruleList = form.getRuleIdList();
        if (productId == null) {
            productId = form.getPdRuleProductList().get(0).getProductId();
            ruleList = form.getPdRuleProductList().stream().map(each -> each.getRuleId()).collect(Collectors.toList());
        }

        return JSON.toJSONString(ThreeTuple.newInstance(productId,classify,ruleList));
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
