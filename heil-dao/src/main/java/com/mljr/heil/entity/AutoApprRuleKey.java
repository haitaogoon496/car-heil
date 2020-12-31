package com.mljr.heil.entity;


import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

public class AutoApprRuleKey extends BaseEntity{
    @ApiModelProperty(value = "规则id")
    private String ruleId;
    @ApiModelProperty(value = "D-直营,C-渠道")
    private String sysIdn;

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }

    public String getSysIdn() {
        return sysIdn;
    }

    public void setSysIdn(String sysIdn) {
        this.sysIdn = sysIdn == null ? null : sysIdn.trim();
    }
}