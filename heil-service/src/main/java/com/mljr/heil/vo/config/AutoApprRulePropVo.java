package com.mljr.heil.vo.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @Date : 2018/5/2$ 14:44$
 * @Author : liht
 */
@Data
public class AutoApprRulePropVo {
    @ApiModelProperty(value = "主键id")
    private String id;
    @ApiModelProperty(value = "规则ID")
    private String ruleId;
    @ApiModelProperty(value = "属性编码")
    private String propCode;
    @ApiModelProperty(value = "属性名称")
    private String propName;
    @ApiModelProperty(value = "操作符")
    private String opIdn;
    @ApiModelProperty(value = "属性值")
    private String propValue;
    @ApiModelProperty(value = "真正的value值操作，后续这个字段改为真正的prop_value，而prop_value变成prop_value_bak")
    private String propValueBak;
    @ApiModelProperty(value = "属性值类型;1:string，字符串;20:integger，整型;21:bigDecimal，浮点类型;30:express，表达式")
    private String propValueType;
    @ApiModelProperty(value = "状态：1-启用，2-停用")
    private String status;
}
