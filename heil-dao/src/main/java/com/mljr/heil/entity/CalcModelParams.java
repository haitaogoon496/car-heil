package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CalcModelParams extends BaseEntity{

    @ApiModelProperty(value = "参数名称")
    private String paramName;
    @ApiModelProperty(value = "参数描述")
    private String paramDesc;
    @ApiModelProperty(value = "参数状态（0:停用 ，1:启用）")
    private Byte status;
    @ApiModelProperty(value = "是否自定义（0:不是 ，1:是自定义）")
    private Byte custom;

    private Date createTime;

    private Date updateTime;
    @ApiModelProperty(value = "公式code")
    private String formulaCode;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc == null ? null : paramDesc.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getCustom() {
        return custom;
    }

    public void setCustom(Byte custom) {
        this.custom = custom;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFormulaCode() {
        return formulaCode;
    }

    public void setFormulaCode(String formulaCode) {
        this.formulaCode = formulaCode;
    }
}