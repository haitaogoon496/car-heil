package com.mljr.heil.form;

import com.lyqc.base.common.validation.EnumValidation;
import com.lyqc.base.enums.ProductConstant;
import com.mljr.heil.base.form.BaseRuleForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @Date : 2018/6/25$ 11:52$
 * @Author : liht
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PdFeeProductForm extends BaseRuleForm {
    @ApiModelProperty(value = "产品id")
    private Integer productId;
    @ApiModelProperty(value = "产品名称")
    private String productName;
    @ApiModelProperty(value = "产品类型id")
    private Integer productTypeId;
    @ApiModelProperty(value = "关联续保押金规则id")
    private Integer resId;
    @ApiModelProperty(value = "规则名称")
    private String ruleName;
    @ApiModelProperty(value = "产品idList")
    private List<Integer> productIdList;
    @ApiModelProperty(value = "规则类型")
    @EnumValidation(enums = ProductConstant.FeeRuleClassifyEnum.class,message = "pdFeeClassify不正确,请核实后！正确值:{0}")
    private Integer pdFeeClassify;
    @ApiModelProperty(value = "是否绑定")
    private Boolean bindFlag = true;
    @ApiModelProperty(value = "规则id集合")
    private List<Integer> ruleIdList;
    @ApiModelProperty(value = "状态(0-无效、1-有效)")
    private Integer status;
    @ApiModelProperty(value = "tag标签对应的业务类型")
    private Byte buzType;

}
