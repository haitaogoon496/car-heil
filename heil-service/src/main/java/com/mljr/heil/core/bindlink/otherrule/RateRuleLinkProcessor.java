package com.mljr.heil.core.bindlink.otherrule;

import com.alibaba.fastjson.JSONObject;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.heil.core.bindlink.AbstractLinkProcessor;
import com.mljr.heil.core.bindlink.RuleServiceEnum;
import com.mljr.heil.form.RateRuleForm;
import com.mljr.heil.service.rule.RateRuleService;

import java.util.List;
import java.util.stream.Collectors;
/**
 * @description: 利率规则关联处理器
 * @Date : 2018/11/12 下午3:20
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class RateRuleLinkProcessor extends AbstractLinkProcessor {
    /**
     * 构造函数
     */
    public RateRuleLinkProcessor() {
        super("利率规则");
    }

    @Override
    protected void init() {
        classify = TagConstant.BuzTypeEnum.RATE_RULE.getIndex();
        sourceList = batchLinkParam.getRateRuleIds();
        ruleService = context.getServiceMap().get(RuleServiceEnum.RATE);
        ruleDealerService = context.getServiceMap().get(RuleServiceEnum.RATE_DEALER);
    }

    @Override
    protected void emptyInputItem() {
        feeRuleVo.setRateNotExist(sourceList);
    }

    @Override
    public List<Integer> dataList() {
        ruleForm = new RateRuleForm();
        ruleForm.setRuleIds(batchLinkParam.getRateRuleIds());
        List<Integer> des = ((RateRuleService)ruleService).queryList((RateRuleForm) ruleForm).stream().map(rule -> rule.getRuleSeq()).collect(Collectors.toList());
        return des;
    }

    @Override
    public void accept(List<Integer> integers, JSONObject jsonObject) {
        feeRuleVo.setRateNotExist(integers);
        jsonObject.put("buzType",buzType);
    }
}