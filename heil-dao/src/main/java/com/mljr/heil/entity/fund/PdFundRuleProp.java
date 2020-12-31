package com.mljr.heil.entity.fund;

import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PdFundRuleProp extends BaseEntity{
    private Integer id;
    @ApiModelProperty(value = "资金方规则id")
    private Integer fundRuleId;
    @ApiModelProperty(value = "资金方规则属性key")
    private String propName;

    @ApiModelProperty(value = "资金方规则属性val")
    private String propValue;

    @ApiModelProperty(value = "规则户籍")
    private List<PdFundRuleCensus> pdFundRuleCensusList;
}