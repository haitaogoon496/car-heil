package com.mljr.heil.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @Date : 2018/6/28$ 15:14$
 * @Author : liht
 */
@Data
public class ProvinceParam {

    @ApiModelProperty(value = "省份code")
    private String provinceCode;
    @ApiModelProperty(value = "省份名称")
    private String provinceName;
    @ApiModelProperty(value = "城市列表")
    private List<CityParam> cities;
}
