package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;

import java.math.BigDecimal;
/**
 * @description: 人身保险费规则
 * @Date : 2018/2/27 下午2:41
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
public class PdFeeRule extends BaseEntity {
    private static final long serialVersionUID = 558874881864342539L;
    @ApiModelProperty(name="classify",value="费用类别",required = true,dataType="Integer")
    @NotNull(message = "[费用类别]不能为空")
    /**
     * {@link com.lyqc.base.enums.ProductConstant.FeeRuleClassifyEnum#getIndex()}
     */
    private Integer classify;
    @ApiModelProperty(name="ruleName",value="规则名称",required = true,dataType="String")
    @NotNull(message = "[规则名称]不能为空")
    private String ruleName;
    @ApiModelProperty(name="rateValue",value="利率",required = true,dataType="BigDecimal")
    @Length(max = 100,message = "返佣金额必须在0~100之间")
    private BigDecimal rateValue;
    @ApiModelProperty(value = "贷款期限")
    @NotNull(message = "[贷款期限]不能为空")
    private String loanPeriod;
    /**
     * {@link com.lyqc.heil.enums.LicenceTypeEnum#getIndex()}
     */
    @ApiModelProperty(value = "私牌类型")
    private String licenseType;
    @ApiModelProperty(value = "是否适用所有经销商")
    private Byte isAllDealer;
    @ApiModelProperty(value = "是否适用所有产品")
    private Byte isAllProduct;
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
    /**
     * {@link com.mljr.heil.common.enums.DictionaryConstant.YesOrNoEnum#getIndex()}
     */
    @ApiModelProperty(value = "是否二手车(0-新车、1-二手车)")
    private String isOld;
    @ApiModelProperty(value = "车类(0-乘用车、1-LCV、2-MMPV、3-商用车)")
    private String isLcv;
    @ApiModelProperty(value = "扩展字段(结构化数据，如配置公式，JSON字符串格式类型)")
    private String extendProps;
    @ApiModelProperty(value = "车龄(月)下限")
    private Integer vehicleAgeMin;
    @ApiModelProperty(value = "车龄(月)上限")
    private Integer vehicleAgeMax;
    @ApiModelProperty(value = "返佣金额")
    private BigDecimal rebateValue;
    @ApiModelProperty(value = "状态")
    private Byte status;
}