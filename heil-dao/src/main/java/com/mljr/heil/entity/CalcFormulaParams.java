package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CalcFormulaParams extends BaseEntity {
    private Integer id;

    private String formulaCode;

    private Integer paramId;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "参数ID列表字符串")
    private String paramIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormulaCode() {
        return formulaCode;
    }

    public void setFormulaCode(String formulaCode) {
        this.formulaCode = formulaCode == null ? null : formulaCode.trim();
    }

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
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

    public String getParamIds() {
        return paramIds;
    }

    public void setParamIds(String paramIds) {
        this.paramIds = paramIds;
    }
}