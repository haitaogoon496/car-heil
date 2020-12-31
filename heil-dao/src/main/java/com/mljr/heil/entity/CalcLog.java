package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CalcLog extends BaseEntity{

    @ApiModelProperty(value = "订单号")
    private String appCode;
    @ApiModelProperty(value = "请求参数")
    private String requestParam;
    @ApiModelProperty(value = "响应结果")
    private String responseResult;
    @ApiModelProperty(value = "目标源")
    private String source;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 业务类型（1：计算引擎调用；2：Loanapply业务追踪）
     * {@link com.lyqc.base.enums.ProductEngineConstant.CalcLogBuzType}
     */
    @ApiModelProperty(value = "业务类型")
    private Integer buzType;
    /**
     * 是否提交（1：是；0：否）
     * {@link com.mljr.heil.common.enums.DictionaryConstant.YesOrNoEnum}
     */
    @ApiModelProperty(value = "是否提交")
    private Integer isSubmit;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam == null ? null : requestParam.trim();
    }

    public String getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(String responseResult) {
        this.responseResult = responseResult == null ? null : responseResult.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}