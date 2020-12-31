package com.mljr.heil.vo.rule;

import com.mljr.heil.base.vo.BaseRuleVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;

import java.math.BigDecimal;

/**
 * @description: 人身保险费规则 配置VO类
 * @Date : 下午5:13 2018/2/9
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Data
public class PdFeeRuleVo extends BaseRuleVo {
    private static final long serialVersionUID = -1600845940823715543L;
    @ApiModelProperty(name = "id",value = "主键")
    private String id;
    /**
     * {@link com.lyqc.base.enums.ProductConstant.FeeRuleClassifyEnum#getIndex()}
     */
    @ApiModelProperty(name="classify",value="费用类别",required = true,dataType="Integer")
    @NotNull(message = "[费用类别]不能为空")
    private Integer classify;
    @ApiModelProperty(value = "费用类别说明")
    private String classifyDesc;
    @ApiModelProperty(name="ruleName",value="规则名称",required = true,dataType="String")
    @NotNull(message = "[规则名称]不能为空")
    private String ruleName;
    @ApiModelProperty(name="rateValue",value="利率",required = true,dataType="BigDecimal")
    @NotNull(message = "[利率]不能为空")
    @Length(max = 100,message = "利率必须在0~100之间")
    private BigDecimal rateValue;
    @ApiModelProperty(name="loanPeriod",value="贷款期限",required = true,dataType="loanPeriod")
    private String loanPeriod;
    /**
     * {@link com.lyqc.heil.enums.LicenceTypeEnum#getIndex()}
     */
    @ApiModelProperty(value = "私牌类型")
    private String licenseType;
    @ApiModelProperty(value = "私牌类型具体值")
    private String licenseTypeVal;
    @ApiModelProperty(value = "是否适用所有经销商")
    private String isAllDealer;
    @ApiModelProperty(value = "是否适用所有产品")
    private String isAllProduct;
    @ApiModelProperty(value = "实际销售价下限")
    private BigDecimal salePriceMin;
    @ApiModelProperty(value = "实际销售价上限")
    private BigDecimal salePriceMax;
    @ApiModelProperty(value = "车辆贷款金额下限")
    private BigDecimal carLoanAmountMin;
    @ApiModelProperty(value = "车辆贷款金额上限")
    private BigDecimal carLoanAmountMax;
    @ApiModelProperty(value = "首付比下限")
    private Integer paymentScaleMin;
    @ApiModelProperty(value = "首付比上限")
    private Integer paymentScaleMax;
    @ApiModelProperty(value = "二手车里程(KM)下限")
    private Integer vehicleMilesMin;
    @ApiModelProperty(value = "二手车里程(KM)上限")
    private Integer vehicleMilesMax;
    @ApiModelProperty(value = "扩展字段(结构化数据，如配置公式，JSON字符串格式类型)")
    private String extendProps;
    @ApiModelProperty(value = "车龄(月)下限")
    private Integer vehicleAgeMin;
    @ApiModelProperty(value = "车龄(月)上限")
    private Integer vehicleAgeMax;
    @ApiModelProperty(value = "返佣")
    private String rebateValue;
    @ApiModelProperty(value = "状态")
    private String status;

}
