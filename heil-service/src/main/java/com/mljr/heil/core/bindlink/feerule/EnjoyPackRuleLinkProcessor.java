package com.mljr.heil.core.bindlink.feerule;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
/**
 * @description: 超享包关联处理器
 * @Date : 2019/1/16 下午3:32
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class EnjoyPackRuleLinkProcessor extends AbstractFeeRuleLinkProcessor {

    public EnjoyPackRuleLinkProcessor() {
        super("超享包规则");
    }

    @Override
    List<Integer> getInputRuleIds() {
        return batchLinkParam.getEnjoyPackRuleIds();
    }

    @Override
    protected void emptyInputItem() {
        feeRuleVo.setEnjoyPackNotExist(sourceList);
    }

    @Override
    public void accept(List<Integer> integers, JSONObject jsonObject) {
        super.accept(integers, jsonObject);
        feeRuleVo.setEnjoyPackNotExist(integers);
    }
}