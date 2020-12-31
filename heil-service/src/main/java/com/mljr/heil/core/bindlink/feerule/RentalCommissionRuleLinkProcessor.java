package com.mljr.heil.core.bindlink.feerule;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @description: 续保押金关联处理器
 * @Date : 2018/11/13 上午11:39
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class RentalCommissionRuleLinkProcessor extends AbstractFeeRuleLinkProcessor {
    /**
     * 构造函数
     */
    public RentalCommissionRuleLinkProcessor() {
        super("续保押金规则");
    }

    @Override
    List<Integer> getInputRuleIds() {
        return batchLinkParam.getRenewalCommissionRuleIds();
    }

    @Override
    protected void emptyInputItem() {
        feeRuleVo.setRenewalCommissionNotExist(sourceList);
    }

    @Override
    public void accept(List<Integer> integers, JSONObject jsonObject) {
        super.accept(integers, jsonObject);
        feeRuleVo.setRenewalCommissionNotExist(integers);
    }
}