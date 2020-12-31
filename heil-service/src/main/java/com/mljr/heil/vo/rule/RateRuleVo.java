package com.mljr.heil.vo.rule;

import com.mljr.heil.base.vo.BaseRuleVo;
import com.mljr.heil.base.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.NotNull;

@Data
public class RateRuleVo extends BaseRuleVo {
    @ApiModelProperty(name="rateLevel",value="利率档位",required = true,dataType="String")
    @NotNull(message = "[利率档位]不能为空")
    private String rateLevel;
    @ApiModelProperty(name="loanRate",value="贷款利率",required = true,dataType="String")
    @NotNull(message = "[贷款利率]不能为空")
    private String loanRate;
    @ApiModelProperty(value = "高风险费用")
    private String highRate;
    @ApiModelProperty(value = "扩展字段")
    private String extendProps;
}