package com.mljr.heil.voconvertor.rule;

import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.YanbaoRule;
import com.mljr.heil.vo.rule.YanbaoRuleVo;
import com.mljr.heil.voconvertor.VoConvertor;

/**
 * @description: 第二/三年保险费规则 VO转换
 * @Date : 2018/2/9 下午5:17
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class YanbaoRuleVoConvertor extends VoConvertor<YanbaoRuleVo, YanbaoRule> {


    @Override
    public YanbaoRuleVo convert(YanbaoRule bo) {
        YanbaoRuleVo vo = new YanbaoRuleVo();
        vo.setRuleSeq(StringUtils.killNull(bo.getRuleSeq()));
        vo.setRuleSeqName(StringUtils.killNull(bo.getRuleSeqName()));
        vo.setIsOld(StringUtils.killNull(bo.getIsOld()));
        vo.setIsLcv(StringUtils.killNull(bo.getIsLcv()));
        vo.setLoanAmountMiin(StringUtils.doubleToRate(bo.getLoanAmountMiin()));
        vo.setLoanAmountMax(StringUtils.doubleToRate(bo.getLoanAmountMax()));
        vo.setInitScaleMin(StringUtils.doubleToRate(bo.getInitScaleMin()));
        vo.setInitScaleMax(StringUtils.doubleToRate(bo.getInitScaleMax()));
        vo.setrLoanPeriods(StringUtils.killNull(bo.getrLoanPeriods()));
        vo.setIsAllDealer(StringUtils.killNull(bo.getIsAllDealer()));
        vo.setProSeq(StringUtils.killNull(bo.getProSeq()));
        vo.setBeginDate(bo.getBeginDate());
        vo.setEndDate(bo.getEndDate());
        vo.setVar(StringUtils.killNull(bo.getVar()));
        vo.setUserId(StringUtils.killNull(bo.getUserId()));
        vo.setUserName(StringUtils.killNull(bo.getUserName()));
        vo.setRemarks(StringUtils.killNull(bo.getRemarks()));
        vo.setRule1(StringUtils.killNull(bo.getRule1()));
        vo.setRule2(StringUtils.killNull(bo.getRule2()));
        vo.setRule3(StringUtils.killNull(bo.getRule3()));
        vo.setRule4(StringUtils.killNull(bo.getRule4()));

        vo.setClassify(bo.getClassify());
        vo.setTcList(bo.getTcList());
        vo.setStatus(StringUtils.killNull(bo.getStatus()));
        vo.convertEnum();
        return vo;
    }
}
