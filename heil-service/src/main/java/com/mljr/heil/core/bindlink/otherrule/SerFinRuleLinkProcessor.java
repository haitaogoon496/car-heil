package com.mljr.heil.core.bindlink.otherrule;

import com.alibaba.fastjson.JSONObject;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.heil.core.bindlink.AbstractLinkProcessor;
import com.mljr.heil.core.bindlink.RuleServiceEnum;
import com.mljr.heil.form.SerFinRuleForm;
import com.mljr.heil.service.rule.SerFinRuleService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 平台费规则关联处理器
 * @Date : 2018/11/12 下午3:20
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class SerFinRuleLinkProcessor extends AbstractLinkProcessor {
    /**
     * 构造函数
     */
    public SerFinRuleLinkProcessor() {
        super("平台费规则");
    }

    @Override
    protected void init() {
        classify = TagConstant.BuzTypeEnum.SER_FIN_RULE.getIndex();
        sourceList = batchLinkParam.getSerFinRuleIds();
        ruleService = context.getServiceMap().get(RuleServiceEnum.SER_FIN);
        ruleDealerService = context.getServiceMap().get(RuleServiceEnum.SER_FIN_DEALER);
    }

    @Override
    protected void emptyInputItem() {
        feeRuleVo.setSerFinNotExist(sourceList);
    }

    @Override
    public List<Integer> dataList() {
        ruleForm = new SerFinRuleForm();
        ruleForm.setRuleIds(batchLinkParam.getSerFinRuleIds());
        List<Integer> des = ((SerFinRuleService)ruleService).queryList((SerFinRuleForm) ruleForm).stream().map(rule -> rule.getRuleSeq()).collect(Collectors.toList());
        return des;
    }

    @Override
    public void accept(List<Integer> integers, JSONObject jsonObject) {
        feeRuleVo.setSerFinNotExist(integers);
        jsonObject.put("buzType",buzType);
    }
}