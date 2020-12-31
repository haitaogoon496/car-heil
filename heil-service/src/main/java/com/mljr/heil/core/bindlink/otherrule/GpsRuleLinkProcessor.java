package com.mljr.heil.core.bindlink.otherrule;

import com.alibaba.fastjson.JSONObject;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.heil.core.bindlink.AbstractLinkProcessor;
import com.mljr.heil.core.bindlink.RuleServiceEnum;
import com.mljr.heil.form.GpsRuleForm;
import com.mljr.heil.service.rule.GpsRuleService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: GPS规则关联处理器
 * @Date : 2018/10/19 下午6:04
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class GpsRuleLinkProcessor extends AbstractLinkProcessor {
    /**
     * 构造函数
     */
    public GpsRuleLinkProcessor() {
        super("GPS规则");
    }

    @Override
    protected void init() {
        classify = TagConstant.BuzTypeEnum.GPS_RULE.getIndex();
        sourceList = batchLinkParam.getGpsRuleIds();
        ruleService = context.getServiceMap().get(RuleServiceEnum.GPS);
        ruleDealerService = context.getServiceMap().get(RuleServiceEnum.GPS_DEALER);
    }

    @Override
    protected void emptyInputItem() {
        feeRuleVo.setGpsNotExist(sourceList);
    }

    @Override
    public List<Integer> dataList() {
        ruleForm = new GpsRuleForm();
        ruleForm.setRuleIds(batchLinkParam.getGpsRuleIds());
        List<Integer> des = ((GpsRuleService)ruleService).queryList((GpsRuleForm) ruleForm).stream().map(rule -> rule.getRuleSeq()).collect(Collectors.toList());
        return des;
    }

    @Override
    public void accept(List<Integer> integers, JSONObject jsonObject) {
        feeRuleVo.setGpsNotExist(integers);
        jsonObject.put("buzType",buzType);
    }
}
