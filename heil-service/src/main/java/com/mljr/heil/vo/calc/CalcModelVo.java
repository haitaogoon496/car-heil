package com.mljr.heil.vo.calc;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @description:
 * @Date : 2018/3/30$ 16:49$
 * @Author : liht
 */
public class CalcModelVo implements Serializable{
    private static final long serialVersionUID = 8240824614319297628L;
    @ApiModelProperty(value = "主键id")
    private String id;
    @ApiModelProperty(value = "产品类型id")
    private String productTypeId;
    @ApiModelProperty(value = "产品id")
    private String productId;
    @ApiModelProperty(value = "费用规则")
    private String ruleType;
    @ApiModelProperty(value = "费用规则对应id")
    private String ruleId;
    @ApiModelProperty(value = "公式编码")
    private String formulaCode;
    @ApiModelProperty(value = "公式内容")
    private String formulaContent;
    @ApiModelProperty(value = "模型编码")
    private String modelCode;
    @ApiModelProperty(value = "公式名称")
    private String formulaName;
    @ApiModelProperty(value = "取整方式，0四舍五入，1向上取整，2，向下取整")
    private String roundType;
    @ApiModelProperty(value = "取整方式，0四舍五入，1向上取整，2，向下取整")
    private String roundTypeDesc;
    @ApiModelProperty(value = "进位精度，-2\t保留到百位,-1\t保留到十位,0\t保留到整数,1\t小数点后保留1位, 2\t小数点后保留2位")
    private String scale;
    @ApiModelProperty(value = "进位精度，-2\t保留到百位,-1\t保留到十位,0\t保留到整数,1\t小数点后保留1位, 2\t小数点后保留2位")
    private String scaleDesc;
    @ApiModelProperty(value = "状态（0:停用， 1:启用）")
    private String status;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "公式类型")
    private String formulaType;
    @ApiModelProperty(value = "产品分类ID/产品ID/规则ID")
    private String unionDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRoundTypeDesc() {
        return roundTypeDesc;
    }

    public void setRoundTypeDesc(String roundTypeDesc) {
        this.roundTypeDesc = roundTypeDesc;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getFormulaCode() {
        return formulaCode;
    }

    public void setFormulaCode(String formulaCode) {
        this.formulaCode = formulaCode;
    }

    public String getFormulaContent() {
        return formulaContent;
    }

    public void setFormulaContent(String formulaContent) {
        this.formulaContent = formulaContent;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getRoundType() {
        return roundType;
    }

    public void setRoundType(String roundType) {
        this.roundType = roundType;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getScaleDesc() {
        return scaleDesc;
    }

    public void setScaleDesc(String scaleDesc) {
        this.scaleDesc = scaleDesc;
    }

    public String getStatus() {
        return status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(String formulaType) {
        this.formulaType = formulaType;
    }

    public String getUnionDesc() {
        return unionDesc;
    }

    public void setUnionDesc(String unionDesc) {
        this.unionDesc = unionDesc;
    }

    /**
     * 判断是否自定义公式
     * @return
     */
    public boolean isDefaultFormulaType(){
        return "0".equals(productTypeId) && "0".equals(productId) && "0".equals(ruleId);
    }
}
