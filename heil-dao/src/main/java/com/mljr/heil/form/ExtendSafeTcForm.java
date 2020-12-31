package com.mljr.heil.form;

import com.lyqc.base.common.BaseForm;

/**
 * @description:
 * @Date : 2018/2/12$ 11:28$
 * @Author : liht
 */
public class ExtendSafeTcForm extends BaseForm{

    /**
     * 规则id
     */
    private Integer ruleSeq;

    public ExtendSafeTcForm(Integer ruleSeq) {
        this.ruleSeq = ruleSeq;
    }

    public Integer getRuleSeq() {
        return ruleSeq;
    }

    public void setRuleSeq(Integer ruleSeq) {
        this.ruleSeq = ruleSeq;
    }
}
