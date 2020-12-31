package com.mljr.heil.vo.rule;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @description:
 * @Date : 2018/3/5$ 16:02$
 * @Author : liht
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerFinRateVo {

    private String id;
    @ApiModelProperty(value = "基础平台费")
    private String basicRate;

    private String ruleSeq;

    private String serFinRate;

    private String serFinRebateRate;
    @ApiModelProperty(value = "高风险融费率")
    private String highRate;
    @ApiModelProperty(value = "扩展字段")
    private String extendProps;
}
