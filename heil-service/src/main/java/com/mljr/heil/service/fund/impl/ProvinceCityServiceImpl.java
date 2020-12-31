package com.mljr.heil.service.fund.impl;

import com.mljr.heil.client.ProvinceCityApi;
import com.mljr.heil.param.CityParam;
import com.mljr.heil.param.ProvinceParam;
import com.mljr.heil.service.fund.ProvinceCityService;
import com.sun.tools.internal.xjc.outline.PackageOutline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @Date : 2018/6/28$ 16:12$
 * @Author : liht
 */
@Service
public class ProvinceCityServiceImpl implements ProvinceCityService{
    @Autowired
    private ProvinceCityApi provinceCityApi;

    @Override
    public List<ProvinceParam> getProvinces() {
        return provinceCityApi.getProvinces();
    }

    @Override
    public List<CityParam> getCities(String provinceCode) {
        return provinceCityApi.getCities(provinceCode);
    }
}
