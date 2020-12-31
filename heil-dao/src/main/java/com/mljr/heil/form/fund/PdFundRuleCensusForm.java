package com.mljr.heil.form.fund;

import com.lyqc.base.common.BaseForm;
import com.mljr.heil.entity.fund.PdFundRuleCensus;

/**
 * @description:
 * @Date : 2018/6/13$ 10:56$
 * @Author : liht
 */
public class PdFundRuleCensusForm extends BaseForm{
    private Integer fundRuleId;

    public PdFundRuleCensusForm() {
    }

    public PdFundRuleCensusForm(Integer fundRuleId) {
        this.fundRuleId = fundRuleId;
    }

    public Integer getFundRuleId() {
        return fundRuleId;
    }

    public void setFundRuleId(Integer fundRuleId) {
        this.fundRuleId = fundRuleId;
    }
}
