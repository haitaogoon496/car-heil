package com.mljr.heil.form;

import com.mljr.heil.base.form.BaseRuleForm;
import lombok.Data;
import net.sf.oval.constraint.NotNull;
import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.Length;

import java.math.BigDecimal;

/**
 * @description: 平台费利率配置
 * @Date : 2018/2/11 17:00
 * @Author : lihaitao
 */
@Data
public class SerFinRuleForm extends BaseRuleForm {

    private static final long serialVersionUID = 5712562973876418082L;
    @NotNull(message = "ruleId不能为空")
    private Integer ruleId;

    private String ruleSeqName;
    private String ruleClassName;
    @Length(max = 100,message = "返佣率必须在0~100")
    @ApiModelProperty(value = "平台费率")
    private BigDecimal serFinRate;
    @ApiModelProperty(value = "平台费返佣率")
    @Length(max = 100,message = "返佣率必须在0~100")
    private BigDecimal serFinRebateRate;
    private Integer ruleSeq;

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleSeqName() {
        return ruleSeqName;
    }

    public void setRuleSeqName(String ruleSeqName) {
        this.ruleSeqName = ruleSeqName;
    }

    public String getRuleClassName() {
        return ruleClassName;
    }

    public void setRuleClassName(String ruleClassName) {
        this.ruleClassName = ruleClassName;
    }

    public BigDecimal getSerFinRate() {
        return serFinRate;
    }

    public void setSerFinRate(BigDecimal serFinRate) {
        this.serFinRate = serFinRate;
    }

    public BigDecimal getSerFinRebateRate() {
        return serFinRebateRate;
    }

    public void setSerFinRebateRate(BigDecimal serFinRebateRate) {
        this.serFinRebateRate = serFinRebateRate;
    }
}
