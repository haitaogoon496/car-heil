package com.mljr.heil.form;

import com.mljr.heil.base.form.BaseRuleForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.Length;

import java.math.BigDecimal;

/**
 * @description: 利率规则配置Form类
 * @Date : 2018/6/10 上午10:34
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
public class RateRuleForm extends BaseRuleForm {
    private static final long serialVersionUID = 5712562973876418082L;
    
    private Integer conArgType;

	@ApiModelProperty(name="rateLevel",value="利率档位",required = false,dataType="String")
	private String rateLevel;
	@ApiModelProperty(name="loanRate",value="利率",required = false,dataType="BigDecimal")
	@Length(max = 100,message = "利率必须在0~100")
	private BigDecimal loanRate;
	@ApiModelProperty(value = "高风险利率")
	private BigDecimal highRate;

}
