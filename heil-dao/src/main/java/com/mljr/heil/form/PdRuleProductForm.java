package com.mljr.heil.form;

import com.lyqc.base.common.BaseForm;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.common.constraint.EnumConstraint;
import com.mljr.heil.base.form.BaseRuleForm;
import com.mljr.heil.entity.PdRuleProduct;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.Max;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @Date : 2018/8/6$ 14:05$
 * @Author : liht
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PdRuleProductForm extends BaseRuleForm {
    @ApiModelProperty(value = "规则类型")
    @EnumConstraint(enumClass="com.lyqc.base.enums.TagConstant&BuzTypeEnum",message = "非法枚举值[classify]")
    private Integer classify;
    @ApiModelProperty(value = "产品id")
    private Integer productId;
    @ApiModelProperty(value = "规则id")
    private Integer ruleId;
    @ApiModelProperty(value = "规则产品关联实体")
    private List<PdRuleProduct> pdRuleProductList;
    @ApiModelProperty(value = "规则id List")
    private List<Integer> ruleIdList;
    @ApiModelProperty(value = "规则id")
    private Integer ruleSeq;
    @ApiModelProperty(name="rateLevel",value="利率档位",required = false,dataType="String")
    private String rateLevel;
    @ApiModelProperty(name="loanRate",value="利率",required = false,dataType="BigDecimal")
    @Length(max = 100,message = "利率必须在0~100")
    private BigDecimal loanRate;
    @ApiModelProperty(name="level",value="GPS档位",required = false,dataType="Integer")
    private Integer level;
    @ApiModelProperty(name="gpsFee",value="GPS费用",required = false,dataType="Double")
    @Length(max = 10000000,message = "GPS费用必须在0~10000000之间")
    @Max(value = 10000000,message = "GPS费用必须在0~10000000之间")
    private Double gpsFee;
    @ApiModelProperty(name="rebate",value="返佣金额",required = false,dataType="BigDecimal")
    @Length(max = 10000000,message = "返佣金额必须在0~10000000之间")
    @Max(value = 10000000,message = "返佣金额必须在0~10000000之间")
    private BigDecimal rebate;
    @ApiModelProperty(name="gpsPro",value="GPS属性",required = false,dataType="String")
    private String gpsPro;
    @ApiModelProperty("第二年/第三年保险类别")
    private Integer yanBaoClassify;
    @ApiModelProperty(value = "人身险/续保押金类别/保险/贴息")
    private Integer pdFeeClassify;
    @ApiModelProperty(value = "延保套餐金额")
    private BigDecimal tcFee;
    @ApiModelProperty(value = "是否绑定")
    private Boolean bindFlag = true;

    @Length(max = 100,message = "返佣率必须在0~100")
    @ApiModelProperty(value = "平台费率")
    private BigDecimal serFinRate;
    @ApiModelProperty(value = "平台费返佣率")
    @Length(max = 100,message = "返佣率必须在0~100")
    private BigDecimal serFinRebateRate;
    @ApiModelProperty(value = "状态 1：有效，0：无效")
    private Integer status;
}
