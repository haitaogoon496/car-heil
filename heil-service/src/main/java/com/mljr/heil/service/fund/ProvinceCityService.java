package com.mljr.heil.service.fund;

import com.mljr.heil.param.CityParam;
import com.mljr.heil.param.ProvinceParam;

import java.util.List;

/**
 * @description:
 * @Date : 2018/6/28$ 16:11$
 * @Author : liht
 */
public interface ProvinceCityService {
    List<ProvinceParam> getProvinces();

    List<CityParam> getCities(String provinceCode);
}
