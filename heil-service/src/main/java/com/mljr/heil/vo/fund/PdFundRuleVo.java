package com.mljr.heil.vo.fund;

import com.mljr.heil.base.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @description:
 * @Date : 2018/6/13$ 11:09$
 * @Author : liht
 */
public class PdFundRuleVo extends BaseVo{
    private String id;
    @ApiModelProperty(value = "资金方id")
    private String fundId;
    @ApiModelProperty(value = "资金方名称")
    private String fundName;
    @ApiModelProperty(value = "准入规则代码")
    private String ruleNo;
    @ApiModelProperty(value = "准入规则名称")
    private String ruleName;
    @ApiModelProperty(value = "状态(1:有效 0:无效)")
    private String status;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "创建人")
    private String createUserName;
    @ApiModelProperty(value = "创建时间")
    private String modifyTime;
    @ApiModelProperty(value = "修改人")
    private String modifyUserName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFundId() {
        return fundId;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUserName() {
        return modifyUserName;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName;
    }
}
