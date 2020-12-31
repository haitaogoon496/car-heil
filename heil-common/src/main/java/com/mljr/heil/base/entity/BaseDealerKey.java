package com.mljr.heil.base.entity;

import java.io.Serializable;

/**
 * @description:
 * @Date : 上午11:42 2018/2/5
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class BaseDealerKey implements Serializable {
    private static final long serialVersionUID = 6142735357894309618L;
    private Integer ruleSeq;

    private Integer dealerCode;

    public Integer getRuleSeq() {
        return ruleSeq;
    }

    public void setRuleSeq(Integer ruleSeq) {
        this.ruleSeq = ruleSeq;
    }

    public Integer getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(Integer dealerCode) {
        this.dealerCode = dealerCode;
    }
}
