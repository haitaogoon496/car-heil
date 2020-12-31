package com.mljr.heil.voconvertor.fund;

import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.fund.PdFundRule;
import com.mljr.heil.vo.fund.PdFundRuleVo;
import com.mljr.heil.voconvertor.VoConvertor;
import com.mljr.util.TimeTools;

/**
 * @description:
 * @Date : 2018/2/7$ 13:55$
 * @Author : liht
 */
public class PdFundRuleVoConvertor extends VoConvertor<PdFundRuleVo, PdFundRule> {


    @Override
    public PdFundRuleVo convert(PdFundRule bo) {

        PdFundRuleVo vo = new PdFundRuleVo();
        vo.setId(StringUtils.killNull(bo.getId()));
        vo.setFundId(StringUtils.killNull(bo.getFundId()));
        vo.setFundName(StringUtils.killNull(bo.getFundName()));
        vo.setRuleNo(StringUtils.killNull(bo.getRuleNo()));
        vo.setRuleName(StringUtils.killNull(bo.getRuleName()));
        vo.setStatus(StringUtils.killNull(bo.getStatus()));
        vo.setRemark(StringUtils.killNull(bo.getRemark()));
        vo.setCreateTime(TimeTools.format4YYYYMMDDHHMISS(bo.getCreateTime()));
        vo.setCreateUserName(StringUtils.killNull(bo.getCreateUserName()));
        vo.setModifyTime(TimeTools.format4YYYYMMDDHHMISS(bo.getModifyTime()));
        vo.setModifyUserName(StringUtils.killNull(bo.getModifyUserName()));
        return vo;
    }
}
