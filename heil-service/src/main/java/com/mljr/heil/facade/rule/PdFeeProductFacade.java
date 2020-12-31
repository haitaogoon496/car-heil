package com.mljr.heil.facade.rule;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.annotation.LogMonitor;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.PdFeeProduct;
import com.mljr.heil.entity.PdFeeRule;
import com.mljr.heil.entity.PdRuleProduct;
import com.mljr.heil.entity.Product;
import com.mljr.heil.form.PdFeeProductForm;
import com.mljr.heil.form.PdRuleProductForm;
import com.mljr.heil.service.product.PdRuleProductService;
import com.mljr.heil.service.rule.PdFeeProductService;
import com.mljr.heil.service.rule.PdFeeRuleService;
import com.mljr.heil.vo.product.ProductVo;
import com.mljr.heil.vo.rule.PdFeeRuleVo;
import com.mljr.heil.voconvertor.product.ProductVoConvertor;
import com.mljr.heil.voconvertor.rule.PdFeeRuleVoConvertor;
import com.mljr.util.CollectionsTools;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: 续保押金和产品关联操作
 * @Date : 2018/6/25 14:08
 * @Author : lihaitao
 */
@Component
public class PdFeeProductFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.PD_FEE_PRODUCT.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.LIFE_INSURANSE;
    @Autowired
    private PdFeeProductService pdFeeProductService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private PdFeeRuleService pdFeeRuleService;
    @Autowired
    private PdRuleProductService pdRuleProductService;

    /**
     * 分页查出当前续保押金绑定产品列表
     * @param form
     * @return
     */
    @LogMonitor("费用规则配置产品")
    public Result<PageVO<ProductVo>> loadBindRecords(PageForm<PdFeeProductForm> form) {
        if (form.getForm() == null || form.getForm().getResId()==null) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM, "续保押金id不能为空");
        }

        List<ProductVo> productVos = Collections.emptyList();
        int count = 0;
        try {
            List<Product> products = pdFeeProductService.selectBindProductByParams(form);
            if (CollectionUtils.isEmpty(products)) {
                return Result.failInEmptyRecord(null);
            }
            productVos = new ProductVoConvertor().convertList(products);
            count = pdFeeProductService.getBindProductCount(form);
        }catch (Exception e){
            LOGGER.error("{}loadBindRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return  Result.fail(new PageVO<>(form.getDraw(),count,productVos));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,productVos));
    }

    /**
     * 分页查出当前续保押金尚未绑定产品列表
     * @param form
     * @return
     */
    @LogMonitor("费用规则配置产品")
    public Result<PageVO<ProductVo>> loadNotBindRecords(PageForm<PdFeeProductForm> form) {
        if (form.getForm() == null || form.getForm().getResId()==null) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM, "续保押金id不能为空");
        }
        List<ProductVo> productVos = Collections.emptyList();
        int count = 0;
        try {
            List<Product> products = pdFeeProductService.selectNotBindProductByParams(form);
            if (CollectionUtils.isEmpty(products)) {
                return Result.failInEmptyRecord(null);
            }
            productVos = new ProductVoConvertor().convertList(products);
            count = pdFeeProductService.getNotBindProductCount(form);
        }catch (Exception e){
            LOGGER.error("{}loadNotBindRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return  Result.fail(new PageVO<>(form.getDraw(),count,productVos));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,productVos));
    }

    /**
     * 分页查出当前产品绑定的费用规则
     * @param form
     * @return
     */
    @LogMonitor("车贷产品-查询关联的费用规则")
    public Result<PageVO<PdFeeRuleVo>> loadBindFeeRule(PageForm<PdFeeProductForm> form) {
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(form.getForm());
        if (CollectionsTools.isNotEmpty(violations)) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM.getIndex(), violations.get(0).getMessage());
        }

        if (form.getForm() == null || form.getForm().getProductId()==null) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM, "产品id不能为空");
        }
        if (form.getForm().getPdFeeClassify() == null) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM, "规则类型不能为空");
        }
        TagConstant.BuzTypeEnum buzTypeEnum = TagConstant.BuzTypeEnum.getTagTypeByClassify(form.getForm().getPdFeeClassify());
        if (buzTypeEnum != null) {
            form.getForm().setBuzType((byte) buzTypeEnum.getIndex());
        }
        List<PdFeeRuleVo> pdFeeRuleVos = Collections.emptyList();
        int count = 0;
        try {
            form.getForm().setStatus(1);
            List<PdFeeRule> pdFeeRules = pdFeeProductService.queryPdFeeRulesForProduct(form);
            if (CollectionUtils.isEmpty(pdFeeRules)) {
                return Result.failInEmptyRecord(null);
            }
            pdFeeRuleVos = new PdFeeRuleVoConvertor().convertList(pdFeeRules);
            count = pdFeeProductService.queryPdFeeRulesForProductCount(form);
        }catch (Exception e){
            LOGGER.error("{}loadBindRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return  Result.fail(new PageVO<>(form.getDraw(),count,pdFeeRuleVos));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,pdFeeRuleVos));
    }

    /**
     * 分页查出当前产品未绑定的费用规则
     * @param form
     * @return
     */
    @LogMonitor("车贷产品-查询关联的费用规则")
    public Result<PageVO<PdFeeRuleVo>> loadNotBindFeeRule(PageForm<PdFeeProductForm> form) {
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(form.getForm());
        if (CollectionsTools.isNotEmpty(violations)) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM.getIndex(), violations.get(0).getMessage());
        }
        if (form.getForm() == null || form.getForm().getProductId()==null) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM, "产品id不能为空");
        }
        if (form.getForm().getPdFeeClassify() == null) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM, "规则类型不能为空");
        }
        form.getForm().setBindFlag(false);
        return loadBindFeeRule(form);
    }

    /**
     *  续保押金 批量绑定产品
     * @param list
     * @return
     */
    @Transactional
    public Result<String>  batchInsert(List<PdFeeProduct> list) {
        LOGGER.info("pdFeeProduct绑定产品参数:{}", JSON.toJSON(list));
        if (CollectionsTools.isEmpty(list)) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM, "请选择要操作的数据");
        }
        pdFeeProductService.batchInsert(list);
        // 临时同步数据
        try {
            Integer ruleId = list.get(0).getResId();
            PdFeeRule pdFeeRule = pdFeeRuleService.queryRecord(ruleId);
            if (pdFeeRule != null) {
                if (pdFeeRule.getClassify() != null && pdFeeRule.getClassify() == 1) {
                    List<PdRuleProduct> pdRuleProducts = new ArrayList<>();
                    list.forEach(pdFeeProduct -> {
                        PdRuleProduct pdRuleProduct = new PdRuleProduct();
                        pdRuleProduct.setProductId(pdFeeProduct.getProductId());
                        pdRuleProduct.setRuleId(pdFeeProduct.getResId());
                        pdRuleProduct.setClassify(new Byte("8"));
                        pdRuleProducts.add(pdRuleProduct);
                    });
                    pdRuleProductService.batchInsert(pdRuleProducts);
                    LOGGER.info("pdFeeProduct同步pdRuleProduct,同步的数据为:{}", JSON.toJSON(pdRuleProducts));
                }
            }
        } catch (Exception e) {
            LOGGER.error("同步数据有问题-", e);
        }

        saveLog(UserOperateLogConstant.AuthTypeEnum.CREATE, JSON.toJSONString(list));
        return Result.suc();
    }

    /**
     *  续保押金 批量解绑产品
     * @param form
     * @return
     */
    @LogMonitor("费用规则配置产品")
    @Transactional
    public Result<String>  batchDelete(PdFeeProductForm form) {
        // 车贷产品管理
        if (form.getPdFeeClassify() != null) {
            if (form.getProductId() == null ||
                    CollectionUtils.isEmpty(form.getRuleIdList()) ) {
                return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM, "请选择要操作产品");
            }
            // 规则管理
        }else{
            if (form.getResId() == null || CollectionUtils.isEmpty(form.getProductIdList())) {
                return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM, "请选择要操作规则");
            }
        }
        pdFeeProductService.batchDelete(form);
        // 暂时同步下数据
        try {
            if (form.getPdFeeClassify() != null) {
                Integer ruleId = form.getRuleIdList().get(0);
                PdFeeRule pdFeeRule = pdFeeRuleService.queryRecord(ruleId);
                if (pdFeeRule != null && pdFeeRule.getClassify() == 1) {
                    PdRuleProductForm pdRuleProductForm = new PdRuleProductForm();
                    if (!CollectionsTools.isEmpty(form.getRuleIdList())) {
                        pdRuleProductForm.setRuleIdList(form.getRuleIdList());
                        pdRuleProductForm.setClassify(8);
                        pdRuleProductForm.setProductId(form.getProductId());
                        pdRuleProductService.deleteByForm(pdRuleProductForm);
                        LOGGER.info("pdFeeProduct同步pdRuleProduct,同步的数据为:{}", JSON.toJSON(pdRuleProductForm));
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("同步数据有问题-", e);
        }

        saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, JSON.toJSONString(form));
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
