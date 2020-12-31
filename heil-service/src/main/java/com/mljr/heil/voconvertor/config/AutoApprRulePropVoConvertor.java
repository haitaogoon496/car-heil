package com.mljr.heil.voconvertor.config;

import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.AutoApprRuleProp;
import com.mljr.heil.vo.config.AutoApprRulePropVo;
import com.mljr.heil.voconvertor.VoConvertor;

/**
 * @description:
 * @Date : 2018/5/2$ 14:45$
 * @Author : liht
 */
public class AutoApprRulePropVoConvertor extends VoConvertor<AutoApprRulePropVo,AutoApprRuleProp> {
    @Override
    public AutoApprRulePropVo convert(AutoApprRuleProp bo) {
        AutoApprRulePropVo vo = new AutoApprRulePropVo();
        vo.setId(StringUtils.killNull(bo.getId()));
        vo.setRuleId(StringUtils.killNull(bo.getRuleId()));
        vo.setPropCode(StringUtils.killNull(bo.getPropCode()));
        vo.setPropName(StringUtils.killNull(bo.getPropName()));
        vo.setPropValue(StringUtils.killNull(bo.getPropValue()));
        vo.setPropValueBak(StringUtils.killNull(bo.getPropValueBak()));
        vo.setPropValueType(StringUtils.killNull(bo.getPropValueType()));
        vo.setOpIdn(StringUtils.killNull(bo.getOpIdn()));
        vo.setStatus(bo.getStatus());
        return vo;
    }
}
