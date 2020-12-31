package com.mljr.heil.vo.rule;

import com.mljr.heil.base.vo.BaseRuleVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Data
public class SerFinRuleVo extends BaseRuleVo {

    private static final long serialVersionUID = 135898893887728975L;
    private String comRates;

    private String products;

    private String workFlow;

    private Boolean commFloatFeeEnable;

    private String commFloatFeeRateMin;

    private String commFloatFeeRateMax;

    private String serFinFee;

    private Integer checked = 0;

    @ApiModelProperty(value = "平台费利率详情")
    private List<SerFinRateVo> serFinRateVos = Collections.emptyList();
    @ApiModelProperty(value = "收取方式")
    private String takenMode;
    @ApiModelProperty(value = "融平台费-基础-融返佣-高风险")
    private List<String> rateList = new ArrayList<>();

}