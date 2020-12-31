package com.mljr.heil.form;

import com.lyqc.base.common.BaseForm;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description:
 * @Date : 2018/3/30$ 16:48$
 * @Author : liht
 */
public class CalcModelForm extends BaseForm {

    @ApiModelProperty(value = "产品类型id")
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
    @ApiModelProperty(value = "状态（0:停用， 1:启用）")
    private Byte status;

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

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
