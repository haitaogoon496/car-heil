package com.mljr.heil.voconvertor.config;

import com.lyqc.product.enums.ConstEnum;
import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.ConfigParams;
import com.mljr.heil.vo.config.ConfigParamsVo;
import com.mljr.heil.voconvertor.VoConvertor;

/**
 * @description:
 * @Date : 2018/4/12$ 18:13$
 * @Author : liht
 */
public class ConfigParamsVoConvertor extends VoConvertor<ConfigParamsVo,ConfigParams> {
    @Override
    public ConfigParamsVo convert(ConfigParams bo) {
        ConfigParamsVo vo = new ConfigParamsVo();
        vo.setId(StringUtils.killNull(bo.getId()));
        vo.setParamKey(StringUtils.killNull(bo.getParamKey()));
        vo.setParamValue(StringUtils.killNull(bo.getParamValue()));
        vo.setParamDesc(StringUtils.killNull(bo.getParamDesc()));
        vo.setStatus(StringUtils.killNull(bo.getStatus()));
        vo.setParamType(bo.getParamType());
        vo.setParamTypeName(ConstEnum.ParamTypeEnum.getNameByIndex(bo.getParamType()));
        return vo;
    }
}
