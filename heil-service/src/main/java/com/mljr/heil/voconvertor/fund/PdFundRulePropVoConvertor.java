package com.mljr.heil.voconvertor.fund;

import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.fund.PdFundRuleProp;
import com.mljr.heil.vo.fund.PdFundRulePropVo;
import com.mljr.heil.voconvertor.VoConvertor;

/**
 * @description:
 * @Date : 2018/2/7$ 13:55$
 * @Author : liht
 */
public class PdFundRulePropVoConvertor extends VoConvertor<PdFundRulePropVo, PdFundRuleProp> {


    @Override
    public PdFundRulePropVo convert(PdFundRuleProp bo) {

        PdFundRulePropVo vo = new PdFundRulePropVo();
        vo.setId(StringUtils.killNull(bo.getId()));
        vo.setFundRuleId(StringUtils.killNull(bo.getFundRuleId()));
        return vo;
    }
}
