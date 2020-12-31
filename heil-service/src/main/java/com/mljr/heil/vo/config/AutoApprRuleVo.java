package com.mljr.heil.vo.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @Date : 2018/4/28$ 15:55$
 * @Author : liht
 */
@Data
public class AutoApprRuleVo implements Serializable{

    private static final long serialVersionUID = 6367452028884882631L;

    @ApiModelProperty(value = "规则id")
    private String ruleId;

    @ApiModelProperty(value = "D-直营,C-渠道")
    private String sysIdn;

    @ApiModelProperty(value = "类型:B-退回、C-取消、R－拒绝")
    private String type;

    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "分类编码")
    private String subType;

    @ApiModelProperty(value = "分类名称")
    private String subTypeName;

    @ApiModelProperty(value = "规则名称")
    private String ruleName;

    @ApiModelProperty(value = "规则类型")
    private String belongName;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "优先级")
    private String ruleSeq;

    @ApiModelProperty(value = "创建时间")
    private String createDate;

    @ApiModelProperty(value = "信息模板")
    private String msgTemplate;

    @ApiModelProperty(value = "业务类型")
    private Integer classify;

    @ApiModelProperty(value = "业务类型名称")
    private String classifyName;

}
