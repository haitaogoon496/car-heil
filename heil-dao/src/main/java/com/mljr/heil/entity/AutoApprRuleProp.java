package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

public class AutoApprRuleProp extends BaseEntity{
    @ApiModelProperty(value = "主键id")
    private Integer id;
    @NotNull(message = "规则id不能为空")
    @ApiModelProperty(value = "规则ID")
    private String ruleId;
    @ApiModelProperty(value = "属性编码")
    private String propCode;
    @ApiModelProperty(value = "属性名称")
    private String propName;
    @ApiModelProperty(value = "操作符")
    private String opIdn;
    @ApiModelProperty(value = "属性值")
    private String propValue;
    @ApiModelProperty(value = "真正的value值操作，后续这个字段改为真正的prop_value，而prop_value变成prop_value_bak")
    private String propValueBak;
    @ApiModelProperty(value = "属性值类型;1:string，字符串;20:integger，整型;21:bigDecimal，浮点类型;30:express，表达式")
    private Integer propValueType;
    @ApiModelProperty(value = "状态：1-启用，2-停用")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }

    public String getPropCode() {
        return propCode;
    }

    public void setPropCode(String propCode) {
        this.propCode = propCode == null ? null : propCode.trim();
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName == null ? null : propName.trim();
    }

    public String getOpIdn() {
        return opIdn;
    }

    public void setOpIdn(String opIdn) {
        this.opIdn = opIdn == null ? null : opIdn.trim();
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue == null ? null : propValue.trim();
    }

    public String getPropValueBak() {
        return propValueBak;
    }

    public void setPropValueBak(String propValueBak) {
        this.propValueBak = propValueBak == null ? null : propValueBak.trim();
    }

    public Integer getPropValueType() {
        return propValueType;
    }

    public void setPropValueType(Integer propValueType) {
        this.propValueType = propValueType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean isInsert() {
        return this.id == null || this.id == 0;
    }
}