package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseRule;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;

import java.math.BigDecimal;
import java.sql.Date;
@Data
public class RateRule extends BaseRule {

    private static final long serialVersionUID = -4233125170042808190L;
    @ApiModelProperty(name="rateLevel",value="利率档位",required = true,dataType="String")
    @NotNull(message = "[利率档位]不能为空")
    private String rateLevel;
    @ApiModelProperty(name="loanRate",value="利率",required = true,dataType="BigDecimal")
    @NotNull(message = "[利率]不能为空")
    @Length(max = 100,message = "利率必须在0~100")
    private BigDecimal loanRate;
    @ApiModelProperty(name="highRate",value="高风险利率",required = true,dataType="BigDecimal")
    @NotNull(message = "[高风险利率]不能为空")
    @Length(max = 100,message = "高风险利率必须在0~100")
    private BigDecimal highRate;
    @ApiModelProperty(value = "扩扎字段")
   private String extendProps;

    @Override
    public void setterIfNull() {
        super.setterIfNull();
    }


}