package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CalcModel extends BaseEntity{

    private Byte productTypeId;
    @ApiModelProperty(value = "产品id")
    private Integer productId;
    @ApiModelProperty(value = "费用规则")
    private Byte ruleType;
    @ApiModelProperty(value = "费用规则对应id")
    private Integer ruleId;
    @ApiModelProperty(value = "公式编码")
    private String formulaCode;
    @ApiModelProperty(value = "公式内容")
    private String formulaContent;
    @ApiModelProperty(value = "公式名称")
    private String formulaName;
    @ApiModelProperty(value = "取整方式，0四舍五入，1向上取整，2，向下取整")
    private Byte roundType;

    @ApiModelProperty(value = "进位精度，-2\t保留到百位,-1\t保留到十位,0\t保留到整数,1\t小数点后保留1位, 2\t小数点后保留2位")
    private Integer scale;
    @ApiModelProperty(value = "状态（0:停用， 1:启用）")
    private Byte status;
    @ApiModelProperty(value = "进位精度，-2\t保留到百位,-1\t保留到十位,0\t保留到整数,1\t小数点后保留1位, 2\t小数点后保留2位")
    private Date createTime;
    @ApiModelProperty(value = "状态（0:停用， 1:启用）")
    private Date updateTime;

    public Byte getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Byte productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Byte getRuleType() {
        return ruleType;
    }

    public void setRuleType(Byte ruleType) {
        this.ruleType = ruleType;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getFormulaContent() {
        return formulaContent;
    }

    public void setFormulaContent(String formulaContent) {
        this.formulaContent = formulaContent == null ? null : formulaContent.trim();
    }

    public String getFormulaCode() {
        return formulaCode;
    }

    public void setFormulaCode(String formulaCode) {
        this.formulaCode = formulaCode == null ? null : formulaCode.trim();
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName == null ? null : formulaName.trim();
    }

    public Byte getRoundType() {
        return roundType;
    }

    public void setRoundType(Byte roundType) {
        this.roundType = roundType;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
}