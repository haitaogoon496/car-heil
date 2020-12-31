package com.mljr.heil.form;

import com.lyqc.base.common.validation.EnumValidation;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.common.constraint.EnumConstraint;
import com.mljr.heil.base.form.BaseRuleForm;
import com.mljr.heil.common.enums.ApplyDealerTableEnum;
import com.mljr.heil.common.util.ValidateUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.NotNull;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 利率规则配置Form类
 * @Date : 2018/6/10 上午10:34
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealerRuleSetForm extends BaseRuleForm {
    private static final long serialVersionUID = 5712562973876418082L;
	private Boolean bindFlag = true;
	private Integer conArgType;


	/*利率规则相关的查询条件*/
	@ApiModelProperty(name="rateLevel",value="利率档位",required = false,dataType="String")
	private String rateLevel;
	@ApiModelProperty(name="loanRate",value="利率",required = false,dataType="BigDecimal")
	@Length(max = 100,message = "利率必须在0~100")
	private BigDecimal loanRate;
	@ApiModelProperty(value = "高风险利率")
	private BigDecimal highRate;

	/*GPS规则相关的查询条件*/
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

	/*平台费规则相关的查询条件*/
	@Length(max = 100,message = "返佣率必须在0~100")
	@ApiModelProperty(value = "平台费率")
	private BigDecimal serFinRate;
	@ApiModelProperty(value = "平台费返佣率")
	@Length(max = 100,message = "返佣率必须在0~100")
	private BigDecimal serFinRebateRate;
	@ApiModelProperty(name="tcFee",value="套餐金额",required = true,dataType="BigDecimal")
	private BigDecimal tcFee;


	/*产品相关的查询条件*/
	@ApiModelProperty(value = "产品所属年份")
	private Integer year;
	@ApiModelProperty(value = "产品对应合同id")
	private Integer productContractId;
	@ApiModelProperty(value = "产品类型")
	private Integer type;
	@ApiModelProperty(value = "产品分类")
	private String typeName;
	@ApiModelProperty(name = "productIdList",value = "主键集合")
	private List<Integer> productIdList;
	@ApiModelProperty(name = "fundId", value = "资金方id")
	private Integer fundId;
	@ApiModelProperty(value = "资金方名称")
	private String fundName;
	@ApiModelProperty(value = "产品状态")
	private Integer status;
	/**
	 * 产品状态
	 */
	private List<String> statusScope;

	/*共有的一些条件*/
	@EnumValidation(enums = TagConstant.BuzTypeEnum.class,message = "buzType不正确,请核实后！正确值:{0}")
	@ApiModelProperty(value = "业务类型")
	@NotNull(message = "buzType不能为空")
	private Integer buzType;
	@NotNull(message = "门店code不能为空")
	@ApiModelProperty(value = "门店code")
	private Integer dealerCode;
	@ApiModelProperty(value = "规则ify（人身险:1,续保押金:2,保险:3,贴息:4,盗抢险:5）")
	private Integer classify;
	@ApiModelProperty(value = "门店关联规则中间表")
	@EnumValidation(enums = ApplyDealerTableEnum.class, message = "dealerTableIndex不合法,请核实！正确值：{0}")
	private Integer dealerTableIndex;
	@ApiModelProperty(value = "table名称")
	private String tableName;
}
