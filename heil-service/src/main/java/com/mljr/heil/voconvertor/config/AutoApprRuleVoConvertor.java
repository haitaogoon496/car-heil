package com.mljr.heil.voconvertor.config;

import com.lyqc.base.enums.AutoApprConstant;
import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.AutoApprRule;
import com.mljr.heil.vo.config.AutoApprRuleVo;
import com.mljr.heil.voconvertor.VoConvertor;
import com.mljr.util.TimeTools;

/**
 * @description:
 * @Date : 2018/2/7$ 13:55$
 * @Author : liht
 */
public class AutoApprRuleVoConvertor extends VoConvertor<AutoApprRuleVo, AutoApprRule> {


    @Override
    public AutoApprRuleVo convert(AutoApprRule bo) {
        AutoApprRuleVo vo = new AutoApprRuleVo();
        vo.setSysIdn(StringUtils.killNull(bo.getSysIdn()));
        vo.setRuleId(StringUtils.killNull(bo.getRuleId()));
        vo.setType(StringUtils.killNull(bo.getType()));
        vo.setTypeName(StringUtils.killNull(bo.getTypeName()));
        vo.setSubType(StringUtils.killNull(bo.getSubType()));
        vo.setSubTypeName(StringUtils.killNull(bo.getSubTypeName()));
        vo.setRuleName(StringUtils.killNull(bo.getRuleName()));
        vo.setBelongName(StringUtils.killNull(bo.getBelongName()));
        vo.setStatus(StringUtils.killNull(bo.getStatus()));
        vo.setRuleSeq(StringUtils.killNull(bo.getRuleSeq()));
        vo.setCreateDate(TimeTools.format4YYYYMMDDHHMISS(bo.getCreateDate()));
        vo.setMsgTemplate(StringUtils.killNull(bo.getMsgTemplate()));
        vo.setClassify(bo.getClassify());
        vo.setClassifyName(AutoApprConstant.ClassifyEnum.getNameByIndex(bo.getClassify()));
        return vo;
    }
}
