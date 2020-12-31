package com.mljr.heil.entity;

import com.lyqc.base.common.validation.EnumValidation;
import com.lyqc.base.enums.AutoApprConstant;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class AutoApprRule extends AutoApprRuleKey {
    /**
     * {@link AutoApprConstant.RuleTypeEnum#getName()}
     */
    @ApiModelProperty(value = "规则策略:B-退回、C-取消、R－拒绝")
    @EnumValidation(enums = AutoApprConstant.RuleTypeEnum.class, method = EnumValidation.InvokeMethod.getName, message = "规则策略不合法,请核实！正确值：{0}")
    private String type;
    @ApiModelProperty(value = "类型名称")
    private String typeName;
    @ApiModelProperty(value = "分类编码")
    private String subType;
    @ApiModelProperty(value = "分类名称")
    private String subTypeName;
    @ApiModelProperty(value = "规则名称")
    private String ruleName;
    /**
     * {@link AutoApprConstant.BelongNameEnum#getName()}
     */
    @ApiModelProperty(value = "规则类型")
    @EnumValidation(enums = AutoApprConstant.BelongNameEnum.class, method = EnumValidation.InvokeMethod.getName, message = "规则类型不合法,请核实！正确值：{0}")
    private String belongName;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "优先级")
    private Byte ruleSeq;

    private Date createDate;
    @ApiModelProperty(value = "信息模板")
    private String msgTemplate;

    /**
     * {@link AutoApprConstant.ClassifyEnum#getIndex()}
     */
    @EnumValidation(enums = AutoApprConstant.ClassifyEnum.class, message = "classify不合法,请核实！正确值：{0}")
    @ApiModelProperty(value = "业务类型")
    private Integer classify;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType == null ? null : subType.trim();
    }

    public String getSubTypeName() {
        return subTypeName;
    }

    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName == null ? null : subTypeName.trim();
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public String getBelongName() {
        return belongName;
    }

    public void setBelongName(String belongName) {
        this.belongName = belongName == null ? null : belongName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Byte getRuleSeq() {
        return ruleSeq;
    }

    public void setRuleSeq(Byte ruleSeq) {
        this.ruleSeq = ruleSeq;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMsgTemplate() {
        return msgTemplate;
    }

    public void setMsgTemplate(String msgTemplate) {
        this.msgTemplate = msgTemplate == null ? null : msgTemplate.trim();
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }
}