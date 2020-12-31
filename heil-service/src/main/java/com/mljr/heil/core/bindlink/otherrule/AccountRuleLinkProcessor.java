package com.mljr.heil.core.bindlink.otherrule;

import com.alibaba.fastjson.JSONObject;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.heil.core.bindlink.AbstractLinkProcessor;
import com.mljr.heil.core.bindlink.RuleServiceEnum;
import com.mljr.heil.form.AccountRuleForm;
import com.mljr.heil.service.rule.AccountRuleService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 账号管理费规则关联处理器
 * @Date : 2018/11/12 下午3:20
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class AccountRuleLinkProcessor extends AbstractLinkProcessor {
    /**
     * 构造函数
     */
    public AccountRuleLinkProcessor() {
        super("账号管理费规则");
    }

    @Override
    protected void init() {
        classify = TagConstant.BuzTypeEnum.ACCOUNT_RULE.getIndex();
        sourceList = batchLinkParam.getAccountRuleIds();
        ruleService = context.getServiceMap().get(RuleServiceEnum.ACCOUNT);
        ruleDealerService = context.getServiceMap().get(RuleServiceEnum.ACCOUNT_DEALER);
    }

    @Override
    protected void emptyInputItem() {
        feeRuleVo.setAccountNotExist(sourceList);
    }

    @Override
    public List<Integer> dataList() {
        ruleForm = new AccountRuleForm();
        ruleForm.setRuleIds(batchLinkParam.getAccountRuleIds());
        List<Integer> des = ((AccountRuleService)ruleService).queryList((AccountRuleForm) ruleForm).stream().map(rule -> rule.getRuleSeq()).collect(Collectors.toList());
        return des;
    }

    @Override
    public void accept(List<Integer> integers, JSONObject jsonObject) {
        feeRuleVo.setAccountNotExist(integers);
        jsonObject.put("buzType",buzType);
    }
}