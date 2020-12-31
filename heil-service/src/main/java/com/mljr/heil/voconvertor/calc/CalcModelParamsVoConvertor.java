package com.mljr.heil.voconvertor.calc;

import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.CalcModelParams;
import com.mljr.heil.vo.calc.CalcModelParamsVo;
import com.mljr.heil.voconvertor.VoConvertor;
import com.mljr.util.TimeTools;

/**
 * @description:
 * @Date : 2018/3/30$ 16:50$
 * @Author : liht
 */
public class CalcModelParamsVoConvertor extends VoConvertor<CalcModelParamsVo,CalcModelParams> {
    @Override
    public CalcModelParamsVo convert(CalcModelParams bo) {
        CalcModelParamsVo vo = new CalcModelParamsVo();
        vo.setId(StringUtils.killNull(bo.getId()));
        vo.setParamName(StringUtils.killNull(bo.getParamName()));
        vo.setParamDesc(StringUtils.killNull(bo.getParamDesc()));
        vo.setStatus(StringUtils.killNull(bo.getStatus()));
        vo.setCustom(StringUtils.killNull(bo.getCustom()));
        vo.setCreateTime(TimeTools.format4YYYYMMDDHHMISS(bo.getCreateTime()));

        return vo;
    }
}
