package com.mljr.heil.param;

import com.mljr.heil.entity.fund.PdFundRuleCensus;
import com.mljr.heil.entity.fund.PdFundRuleProp;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import java.util.List;

/**
 * @description:
 * @Date : 2018/7/6$ 15:24$
 * @Author : liht
 */
@Data
public class PdFundRulePropParam {
    @ApiModelProperty("规则id")
    @NotNull
    private Integer fundRuleId;
    @ApiModelProperty(value = "规则属性列表")
    @NotEmpty
    private List<PdFundRuleProp> pdFundRuleProps;
    @ApiModelProperty(value = "规则户籍")
    private List<PdFundRuleCensus> pdFundRuleCensusList;
}
