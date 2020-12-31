package com.mljr.heil.form;

import com.lyqc.base.common.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lingyu.shang
 */
@Data
public class CalcFormulaParamsForm extends BaseForm {

    @ApiModelProperty(value = "公式编码")
    private String formulaCode;

    @ApiModelProperty(value = "参数ID列表")
    private List<Integer> paramIds;

}
