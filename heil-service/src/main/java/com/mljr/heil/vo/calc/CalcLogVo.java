package com.mljr.heil.vo.calc;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
/**
 * @description: 产品计算log Vo
 * @Date : 2018/4/3 14:04
 * @Author : lihaitao
 */
public class CalcLogVo implements Serializable{

    private static final long serialVersionUID = -5651282713912657648L;
    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "申请单号")
    private String appCode;
    @ApiModelProperty(value = "请求参数")
    private String requestParam;
    @ApiModelProperty(value = "返回参数")
    private String responseResult;
    @ApiModelProperty(value = "调用方")
    private String source;

    private String sourceDesc;
    /**
     * 业务类型（1：计算引擎调用；2：Loanapply业务追踪）
     * {@link com.lyqc.base.enums.ProductEngineConstant.CalcLogBuzType}
     */
    @ApiModelProperty(value = "业务类型")
    private Integer buzType;

    private String buzTypeDesc;
    /**
     * 是否提交（1：是；0：否）
     * {@link com.mljr.heil.common.enums.DictionaryConstant.YesOrNoEnum}
     */
    @ApiModelProperty(value = "是否提交")
    private Integer isSubmit;

    private String isSubmitDesc;
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(String responseResult) {
        this.responseResult = responseResult;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getBuzType() {
        return buzType;
    }

    public void setBuzType(Integer buzType) {
        this.buzType = buzType;
    }

    public Integer getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(Integer isSubmit) {
        this.isSubmit = isSubmit;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSourceDesc() {
        return sourceDesc;
    }

    public void setSourceDesc(String sourceDesc) {
        this.sourceDesc = sourceDesc;
    }

    public String getBuzTypeDesc() {
        return buzTypeDesc;
    }

    public void setBuzTypeDesc(String buzTypeDesc) {
        this.buzTypeDesc = buzTypeDesc;
    }

    public String getIsSubmitDesc() {
        return isSubmitDesc;
    }

    public void setIsSubmitDesc(String isSubmitDesc) {
        this.isSubmitDesc = isSubmitDesc;
    }
}