package com.mljr.heil.form;

import com.lyqc.base.common.BaseForm;
import com.lyqc.base.common.validation.EnumValidation;
import com.lyqc.product.enums.ConstEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @Date : 2018/4/12$ 18:10$
 * @Author : liht
 */
@Data
public class ConfigParamsForm extends BaseForm {

    @ApiModelProperty(value = "参数key")
    private String paramKey;
    @ApiModelProperty(value = "参数值")
    private String paramValue;
    @ApiModelProperty(value = "状态(1:启用 0:暂停)")
    private Byte status;
    @ApiModelProperty(value = "参数类型(1-开关,2-JSON,3-数值,4-字符串)")
    @EnumValidation(enums = ConstEnum.ParamTypeEnum.class, message = "paramType不合法,请核实！正确值：{0}")
    private Integer paramType;
    @ApiModelProperty(name="tag",value="标签")
    private String tag;

}
