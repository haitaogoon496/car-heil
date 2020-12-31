package com.mljr.heil.form.fund;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyqc.base.common.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description:
 * @Date : 2018/6/13$ 10:56$
 * @Author : liht
 */
public class PdFundRuleForm extends BaseForm{
    @ApiModelProperty(value = "规则名称")
    private String ruleName;
    @ApiModelProperty(value = "资金方id")
    private Integer fundId;
    @ApiModelProperty(value = "规则No")
    private String ruleNo;
    @ApiModelProperty(value = "状态")
    private Byte status;
    @ApiModelProperty(value = "创建时间开始")
    private Date createTimeBegin;
    @ApiModelProperty(value = "创建时间结束")
    private Date createTimeEnd;
    @ApiModelProperty(value = "更新时间开始")
    private Date modifyTimeBegin;
    @ApiModelProperty(value = "更新时间结束")
    private Date modifyTimeEnd;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    public String getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(Date createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Date getModifyTimeBegin() {
        return modifyTimeBegin;
    }

    public void setModifyTimeBegin(Date modifyTimeBegin) {
        this.modifyTimeBegin = modifyTimeBegin;
    }

    public Date getModifyTimeEnd() {
        return modifyTimeEnd;
    }

    public void setModifyTimeEnd(Date modifyTimeEnd) {
        this.modifyTimeEnd = modifyTimeEnd;
    }

    @Override
    public String toString() {
        return "PdFundRuleForm{" +
                "ruleName='" + ruleName + '\'' +
                ", fundId=" + fundId +
                '}';
    }
}
