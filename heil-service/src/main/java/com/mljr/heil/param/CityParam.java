package com.mljr.heil.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @Date : 2018/6/28$ 15:17$
 * @Author : liht
 */
@Data
public class CityParam {
    @ApiModelProperty(value = "城市code")
    private String cityCode;
    @ApiModelProperty(value = "城市名称")
    private String cityName;
}
