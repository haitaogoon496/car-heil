package com.mljr.heil.form;
import com.lyqc.base.common.BaseForm;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @description: 计算引擎log
 * @Date : 2018/4/3 13:45
 * @Author : lihaitao
 */
public class CalcLogForm  extends BaseForm{
    @ApiModelProperty(value = "申请单号")
    private String appCode;
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
    /**
     * {@link com.lyqc.base.enums.SystemCodeEnum}
     * 使用枚举name值
     */
    @ApiModelProperty(value = "目标源")
    private String source;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
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
}
