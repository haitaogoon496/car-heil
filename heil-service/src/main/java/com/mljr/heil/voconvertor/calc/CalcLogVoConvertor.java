package com.mljr.heil.voconvertor.calc;

import com.lyqc.base.enums.ProductEngineConstant;
import com.mljr.heil.common.enums.DictionaryConstant;
import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.CalcLog;
import com.mljr.heil.vo.calc.CalcLogVo;
import com.mljr.heil.voconvertor.VoConvertor;
import com.mljr.util.TimeTools;

/**
 * @description:
 * @Date : 2018/3/30$ 16:50$
 * @Author : liht
 */
public class CalcLogVoConvertor extends VoConvertor<CalcLogVo,CalcLog> {
    @Override
    public CalcLogVo convert(CalcLog bo) {
        CalcLogVo vo = new CalcLogVo();
        vo.setId(StringUtils.killNull(bo.getId()));
        vo.setAppCode(StringUtils.killNull(bo.getAppCode()));
        vo.setRequestParam(StringUtils.killNull(bo.getRequestParam()));
        vo.setResponseResult(StringUtils.killNull(bo.getResponseResult()));
        vo.setSource(StringUtils.killNull(bo.getSource()));
        vo.setCreateTime(TimeTools.format4YYYYMMDDHHMISS(bo.getCreateTime()));
        vo.setSourceDesc(bo.getSource());
        vo.setIsSubmitDesc(DictionaryConstant.YesOrNoEnum.getNameByIndex(bo.getIsSubmit()));
        vo.setBuzTypeDesc(ProductEngineConstant.CalcLogBuzType.getNameByIndex(bo.getBuzType()));
        return vo;
    }
}
