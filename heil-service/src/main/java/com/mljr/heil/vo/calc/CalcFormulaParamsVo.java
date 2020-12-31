package com.mljr.heil.vo.calc;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lingyu.shang
 */
@Data
public class CalcFormulaParamsVo implements Serializable {

    @ApiModelProperty(value = "公式编码")
    private String formulaCode;

    @ApiModelProperty(value = "参数列表")
    private List<CalcModelParamsVo> params;

}
