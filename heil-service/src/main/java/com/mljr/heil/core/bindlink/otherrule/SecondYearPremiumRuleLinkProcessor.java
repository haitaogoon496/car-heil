package com.mljr.heil.core.bindlink.otherrule;

import com.alibaba.fastjson.JSONObject;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.heil.core.bindlink.AbstractLinkProcessor;
import com.mljr.heil.core.bindlink.RuleServiceEnum;
import com.mljr.heil.form.YanbaoRuleForm;
import com.mljr.heil.service.rule.YanbaoRuleService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:第二年保险费规则关联处理器
 * @Date : 2018/11/13 上午11:39
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class SecondYearPremiumRuleLinkProcessor extends AbstractLinkProcessor {
    /**
     * 构造函数
     */
    public SecondYearPremiumRuleLinkProcessor() {
        super("第二年保险费规则");
    }

    @Override
    protected void init() {
        classify = TagConstant.BuzTypeEnum.INSURANSE_SECOND_YEAR.getIndex();
        sourceList = batchLinkParam.getSecondYearPremiumRuleIds();
        ruleService = context.getServiceMap().get(RuleServiceEnum.YANBAO);
        ruleDealerService = context.getServiceMap().get(RuleServiceEnum.YANBAO_DEALER);
    }

    @Override
    protected void emptyInputItem() {
        feeRuleVo.setSecondYearPremiumNotExist(sourceList);
    }

    @Override
    public List<Integer> dataList() {
        ruleForm = new YanbaoRuleForm();
        ruleForm.setRuleIds(batchLinkParam.getSecondYearPremiumRuleIds());
        List<Integer> des = ((YanbaoRuleService)ruleService).queryList((YanbaoRuleForm) ruleForm).stream().map(rule -> rule.getRuleSeq()).collect(Collectors.toList());
        return des;
    }

    @Override
    public void accept(List<Integer> integers, JSONObject jsonObject) {
        feeRuleVo.setSecondYearPremiumNotExist(integers);
        jsonObject.put("buzType",buzType);
    }
}