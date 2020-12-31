package com.mljr.heil.core.bindlink.feerule;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @description: 车辆贴息规则关联处理
 * @Date : 2018/11/13 下午5:23
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class CarDiscountRuleLinkProcessor extends AbstractFeeRuleLinkProcessor {

    public CarDiscountRuleLinkProcessor() {
        super("车辆贴息规则");
    }

    @Override
    List<Integer> getInputRuleIds() {
        return batchLinkParam.getCarDiscountRuleIds();
    }

    @Override
    protected void emptyInputItem() {
        feeRuleVo.setCarDiscountNotExist(sourceList);
    }

    @Override
    public void accept(List<Integer> integers, JSONObject jsonObject) {
        super.accept(integers, jsonObject);
        feeRuleVo.setCarDiscountNotExist(integers);
    }
}