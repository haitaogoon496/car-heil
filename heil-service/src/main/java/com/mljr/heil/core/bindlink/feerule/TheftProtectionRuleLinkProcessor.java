package com.mljr.heil.core.bindlink.feerule;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @description: 盗抢险关联处理器
 * @Date : 2018/11/13 上午11:39
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class TheftProtectionRuleLinkProcessor extends AbstractFeeRuleLinkProcessor {

    public TheftProtectionRuleLinkProcessor() {
        super("盗抢险规则");
    }

    @Override
    List<Integer> getInputRuleIds() {
        return batchLinkParam.getTheftProtectionRuleIds();
    }

    @Override
    protected void emptyInputItem() {
        feeRuleVo.setTheftProtectionNotExist(sourceList);
    }

    @Override
    public void accept(List<Integer> integers, JSONObject jsonObject) {
        super.accept(integers, jsonObject);
        feeRuleVo.setTheftProtectionNotExist(integers);
    }
}