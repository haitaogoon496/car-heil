package com.mljr.heil.form;

import com.lyqc.base.common.BaseForm;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: 产品分类Form
 * @Date : 下午7:45 2018/3/3
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class ProductTypeForm extends BaseForm {
    private static final long serialVersionUID = -5252842214132445831L;
    private String status;
    private String pClassCode;
    private String pClassName;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getpClassCode() {
        return pClassCode;
    }

    public void setpClassCode(String pClassCode) {
        this.pClassCode = pClassCode;
    }

    public String getpClassName() {
        return pClassName;
    }

    public void setpClassName(String pClassName) {
        this.pClassName = pClassName;
    }

}
