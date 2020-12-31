package com.mljr.heil.form;

import com.mljr.heil.base.form.BaseRuleForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.Max;

import java.math.BigDecimal;

/**
 * @description: GPS规则配置Form类
 * @Date : 下午5:48 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Data
public class GpsRuleForm extends BaseRuleForm {
    private static final long serialVersionUID = 5712562973876418082L;

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

}
