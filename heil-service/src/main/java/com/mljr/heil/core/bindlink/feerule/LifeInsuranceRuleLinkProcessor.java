package com.mljr.heil.core.bindlink.feerule;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @description: 人身保险费用规则关联处理器
 * @Date : 2018/11/13 上午11:39
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class LifeInsuranceRuleLinkProcessor extends AbstractFeeRuleLinkProcessor {
    /**
     * 构造函数
     */
    public LifeInsuranceRuleLinkProcessor() {
        super("人身保险费规则");
    }

    @Override
    List<Integer> getInputRuleIds() {
        return batchLinkParam.getLifeInsuranceRuleIds();
    }

    @Override
    protected void emptyInputItem() {
        feeRuleVo.setLifeInsuranceNotExist(sourceList);
    }

    @Override
    public void accept(List<Integer> integers, JSONObject jsonObject) {
        super.accept(integers, jsonObject);
        feeRuleVo.setLifeInsuranceNotExist(integers);
    }
}