package com.mljr.heil.form;

import com.lyqc.base.common.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @Date : 2018/4/28$ 15:31$
 * @Author : liht
 */
@Data
public class AutoApprRuleForm extends BaseForm {

    @ApiModelProperty(value = "规则id")
    private String ruleId;

    @ApiModelProperty(value = "分类编码")
    private String subType;

    @ApiModelProperty(value = "类型:B-退回、C-取消、R－拒绝")
    private String type;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "业务类型")
    private Integer classify;

}
