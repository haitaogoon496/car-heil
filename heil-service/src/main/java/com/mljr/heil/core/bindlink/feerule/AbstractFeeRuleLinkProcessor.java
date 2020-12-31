package com.mljr.heil.core.bindlink.feerule;

import com.alibaba.fastjson.JSONObject;
import com.mljr.heil.core.bindlink.AbstractLinkProcessor;
import com.mljr.heil.core.bindlink.LinkProcessBuzType;
import com.mljr.heil.core.bindlink.RuleServiceEnum;
import com.mljr.heil.entity.PdFeeDealer;
import com.mljr.heil.entity.PdFeeProduct;
import com.mljr.heil.form.PdFeeDealerForm;
import com.mljr.heil.form.PdFeeProductForm;
import com.mljr.heil.form.PdFeeRuleForm;
import com.mljr.heil.service.rule.PdFeeDealerService;
import com.mljr.heil.service.rule.PdFeeProductService;
import com.mljr.heil.service.rule.PdFeeRuleService;
import com.mljr.util.CollectionsTools;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 抽象费用规则关联处理器
 * 子类包括（车辆保险、车辆贴息、人身保险费、续保押金、盗抢险、超享包）
 * {@link CarInsuranceRuleLinkProcessor}
 * {@link CarDiscountRuleLinkProcessor}
 * {@link LifeInsuranceRuleLinkProcessor}
 * {@link RentalCommissionRuleLinkProcessor}
 * {@link TheftProtectionRuleLinkProcessor}
 * {@link EnjoyPackRuleLinkProcessor}
 * @Date : 2018/11/13 上午11:39
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Slf4j
public abstract class AbstractFeeRuleLinkProcessor extends AbstractLinkProcessor {
    /**
     * 构造函数
     * @param processorName
     */
    public AbstractFeeRuleLinkProcessor(String processorName) {
        super(processorName);
    }

    /**
     * 抽象获取页面输入规则ID
     * @return
     */
    abstract List<Integer> getInputRuleIds();

    @Override
    protected void init() {
        sourceList = getInputRuleIds();
        ruleService = context.getServiceMap().get(RuleServiceEnum.FEE_RULE);
        ruleDealerService = context.getServiceMap().get(RuleServiceEnum.FEE_RULE_DEALER);
    }

    @Override
    public List<Integer> dataList() {
        ruleForm = new PdFeeRuleForm();
        ruleForm.setRuleIds(sourceList);
        List<Integer> des = ((PdFeeRuleService)ruleService).queryList((PdFeeRuleForm) ruleForm).stream().map(rule -> rule.getId()).collect(Collectors.toList());
        return des;
    }

    @Override
    public void accept(List<Integer> integers, JSONObject jsonObject) {
        jsonObject.put("buzType",buzType);
    }

    @Override
    public void bindDealer(List<Integer> dealerCodes, List<Integer> linkList) {
        if(CollectionsTools.isEmpty(dealerCodes) || CollectionsTools.isEmpty(linkList)){
            return;
        }
        log.info("{},【{}】,linkList={},dealerCodes={},buzType={}", LOG_TITLE,processorName, linkList.toString(), dealerCodes.toString(), buzType);
        dealerCodes.forEach(dealerCode -> {
            List<PdFeeDealer> ruleDealers = new ArrayList<>(linkList.size());
            for(Integer ruleId : linkList){
                ruleDealers.add(new PdFeeDealer(ruleId,dealerCode));
            }
            if(buzType == LinkProcessBuzType.INSERT){
                ruleDealerService.batchInsertIgnore(ruleDealers);
            }
        });
        if(buzType == LinkProcessBuzType.DELETE){
            PdFeeDealerService pdFeeDealerService = (PdFeeDealerService)ruleDealerService;
            pdFeeDealerService.batchDelete(PdFeeDealerForm.builder().dealerScopes(dealerCodes).ruleIdScope(linkList).build());
        }
    }

    @Override
    public void bindProduct(List<Integer> productIds, List<Integer> linkList) {
        if(CollectionsTools.isEmpty(productIds) || CollectionsTools.isEmpty(linkList)){
            return;
        }
        log.info("{},【{}】,linkList={},productIds={},buzType={}", LOG_TITLE,processorName, linkList.toString(), productProcesses.toString(), buzType);
        productIds.forEach(productId -> {
            List<PdFeeProduct> batchList = new ArrayList<>(linkList.size());
            linkList.forEach(eachId -> batchList.add(PdFeeProduct.builder().productId(productId).resId(eachId).build()));
            if(buzType == LinkProcessBuzType.INSERT){
                context.getServiceMap().get(RuleServiceEnum.FEE_PRODUCT).batchInsertIgnore(batchList);
            }
            if(buzType == LinkProcessBuzType.DELETE){
                PdFeeProductForm pdFeeProductForm = PdFeeProductForm.builder().productId(productId).ruleIdList(linkList).build();
                ((PdFeeProductService)context.getServiceMap().get(RuleServiceEnum.FEE_PRODUCT)).batchDelete(pdFeeProductForm);
            }
        });
    }
}