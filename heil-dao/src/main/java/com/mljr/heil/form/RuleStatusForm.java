package com.mljr.heil.form;


import com.lyqc.base.common.BaseForm;
import com.lyqc.base.common.validation.EnumValidation;
import com.mljr.heil.common.enums.ApplyRuleTableEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.NotNull;

/**
 * @author lingyu.shang
 */
@Data
public class RuleStatusForm extends BaseForm {

    private static final long serialVersionUID = 4052080214371012810L;

    @ApiModelProperty(name="ruleId",value="规则ID",required = true,dataType="Integer")
    @NotNull(message = "ruleId不能为空")
    private Integer ruleId;

    /**
     * {@link com.mljr.heil.common.enums.ApplyRuleTableEnum}
     */
    @ApiModelProperty(name="classify",value="规则分类",dataType="Integer")
    @EnumValidation(enums = ApplyRuleTableEnum.class, message = "classify不合法,请核实！正确值：{0}")
    @NotNull(message = "classify不能为空")
    private Integer classify;

    @ApiModelProperty(name="status",value="状态",required = true,dataType="Integer")
    @NotNull(message = "status不能为空")
    private Integer status;

    /**
     * 表名，查询用
     */
    private String tableName;
}
