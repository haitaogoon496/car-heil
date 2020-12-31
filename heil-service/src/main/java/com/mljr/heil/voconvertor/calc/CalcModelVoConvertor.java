package com.mljr.heil.voconvertor.calc;

import com.mljr.heil.common.enums.DictionaryConstant;
import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.CalcModel;
import com.mljr.heil.vo.calc.CalcModelVo;
import com.mljr.heil.voconvertor.VoConvertor;
import com.mljr.util.TimeTools;

/**
 * @description:
 * @Date : 2018/3/30$ 16:50$
 * @Author : liht
 */
public class CalcModelVoConvertor extends VoConvertor<CalcModelVo,CalcModel> {
    @Override
    public CalcModelVo convert(CalcModel bo) {
        CalcModelVo vo = new CalcModelVo();
        vo.setId(StringUtils.killNull(bo.getId()));
        if (bo.getProductTypeId() != null && bo.getProductTypeId() != 0) {
            vo.setProductTypeId(StringUtils.killNull(bo.getProductTypeId()));
        }
        if (bo.getProductId() != null && bo.getProductId() != 0) {
            vo.setProductId(StringUtils.killNull(bo.getProductId()));
        }
        if (bo.getRuleType() != null && bo.getRuleType() != 0) {
            vo.setRuleType(StringUtils.killNull(bo.getRuleType()));
        }
        if (bo.getRuleId() != null && bo.getRuleId() != 0) {
            vo.setRuleId(StringUtils.killNull(bo.getRuleId()));
        }
        vo.setFormulaCode(StringUtils.killNull(bo.getFormulaCode()));
        vo.setFormulaContent(StringUtils.killNull(bo.getFormulaContent()));
        vo.setFormulaName(StringUtils.killNull(bo.getFormulaName()));
        vo.setRoundType(StringUtils.killNull(bo.getRoundType()));
        vo.setRoundTypeDesc(DictionaryConstant.CarryEnum.getNameByIndex(bo.getRoundType()));
        vo.setScale(StringUtils.killNull(bo.getScale()));
        vo.setScaleDesc(DictionaryConstant.PrecisionsForCarProductEnum.getNameByIndex(bo.getScale()));
        vo.setStatus(StringUtils.killNull(bo.getStatus()));
        vo.setCreateTime(TimeTools.format4YYYYMMDDHHMISS(bo.getCreateTime()));
        return vo;
    }
}
