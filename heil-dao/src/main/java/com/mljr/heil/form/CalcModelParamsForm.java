package com.mljr.heil.form;

import com.lyqc.base.common.BaseForm;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description:
 * @Date : 2018/3/30$ 18:11$
 * @Author : liht
 */
public class CalcModelParamsForm extends BaseForm {

    @ApiModelProperty(value = "参数名称")
    private String paramName;
    @ApiModelProperty(value = "参数名称描述")
    private String paramDesc;
    @ApiModelProperty(value = "参数状态（0:停用 ，1:启用）")
    private Byte status;
    @ApiModelProperty(value = "是否自定义参数（0:否 ，1:是）")
    private Byte custom;
    @ApiModelProperty(value = "公式名称")
    private String formulaCode;
    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
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

    public String getFormulaCode() {
        return formulaCode;
    }

    public void setFormulaCode(String formulaCode) {
        this.formulaCode = formulaCode;
    }
}
