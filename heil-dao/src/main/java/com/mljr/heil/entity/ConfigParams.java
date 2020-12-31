package com.mljr.heil.entity;

import com.lyqc.base.common.validation.EnumValidation;
import com.lyqc.product.enums.ConstEnum;
import com.mljr.heil.base.entity.BaseEntity;
import com.mljr.heil.common.enums.DictionaryConstant;
import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

public class ConfigParams extends BaseEntity{
    @ApiModelProperty(value = "主键")
    private Integer id;
    @ApiModelProperty(value = "参数key")
    @NotNull
    private String paramKey;
    @ApiModelProperty(value = "参数值")
    @NotNull
    private String paramValue;
    @ApiModelProperty(value = "参数描述")
    private String paramDesc;

    @ApiModelProperty(value = "状态(1:启用 0:暂停)")
    @EnumValidation(enums = DictionaryConstant.YesOrNoEnum.class, message = "status不合法,请核实！正确值：{0}")
    private Integer status;
    /**
     * {@link ConstEnum.ParamTypeEnum#getIndex()}
     */
    @ApiModelProperty(value = "参数类型(1-开关,2-JSON,3-数值,4-字符串)")
    @EnumValidation(enums = ConstEnum.ParamTypeEnum.class, message = "paramType不合法,请核实！正确值：{0}")
    @NotNull
    private Integer paramType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey == null ? null : paramKey.trim();
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc == null ? null : paramDesc.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getParamType() {
        return paramType;
    }

    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }

    @Override
    public boolean isInsert() {
        return null == this.id || 0 == this.id;
    }
}