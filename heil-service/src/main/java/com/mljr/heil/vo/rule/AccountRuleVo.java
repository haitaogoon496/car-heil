package com.mljr.heil.vo.rule;

import com.mljr.heil.base.vo.BaseRuleVo;
import com.mljr.heil.base.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.sf.oval.constraint.NotNull;

import java.math.BigDecimal;

public class AccountRuleVo extends BaseRuleVo {
    private String topLimitScale;
    private String topLimitFee;

    public String getTopLimitScale() {
        return topLimitScale;
    }

    public void setTopLimitScale(String topLimitScale) {
        this.topLimitScale = topLimitScale;
    }

    public String getTopLimitFee() {
        return topLimitFee;
    }

    public void setTopLimitFee(String topLimitFee) {
        this.topLimitFee = topLimitFee;
    }
}