package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseRule;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class AccountRule extends BaseRule {

    private static final long serialVersionUID = 6317758756153304232L;
    @ApiModelProperty(value = "上限金额")
    private BigDecimal topLimitFee;
    @ApiModelProperty(value = "上限比例")
    private Double topLimitScale;

    public BigDecimal getTopLimitFee() {
        return topLimitFee;
    }

    public void setTopLimitFee(BigDecimal topLimitFee) {
        this.topLimitFee = topLimitFee;
    }

    public Double getTopLimitScale() {
        return topLimitScale;
    }

    public void setTopLimitScale(Double topLimitScale) {
        this.topLimitScale = topLimitScale;
    }

    @Override
    public void setterIfNull() {
        super.setterIfNull();
    }
}