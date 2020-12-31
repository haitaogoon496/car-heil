package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SerFinRate extends BaseEntity{
    @ApiModelProperty(value = "服务费规则ID")
    private Integer ruleSeq;
    @ApiModelProperty(value = "基础平台费")
    private BigDecimal basicRate;
    @ApiModelProperty(value = "费率")
    private Double serFinRate;

    private BigDecimal serFinRebateRate;

    @NotNull
    @Max(value = 100,message = "最大值不能超过100")
    @Min(value = 0,message = "最小值不能小于0")
    @ApiModelProperty(value = "高风险融费率")
    private BigDecimal highRate;
    @ApiModelProperty(value = "扩展字段")
    private String extendProps;
}