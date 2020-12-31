package com.mljr.heil.form;

import com.lyqc.base.common.BaseForm;
import com.mljr.heil.base.form.BaseRuleForm;

import java.math.BigDecimal;

/**
 * @description:
 * @Date : 2018/2/12$ 16:21$
 * @Author : liht
 */
public class AccountRuleForm extends BaseRuleForm {

    private BigDecimal topLimitScale;

    public BigDecimal getTopLimitScale() {
        return topLimitScale;
    }

    public void setTopLimitScale(BigDecimal topLimitScale) {
        this.topLimitScale = topLimitScale;
    }
}
