package com.mljr.heil.voconvertor.rule;

import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.RateRule;
import com.mljr.heil.vo.rule.RateRuleVo;
import com.mljr.heil.voconvertor.VoConvertor;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @description:
 * @Date : 2018/2/7$ 13:55$
 * @Author : liht
 */
public class RateRuleVoConvertor extends VoConvertor<RateRuleVo, RateRule> {


    @Override
    public RateRuleVo convert(RateRule bo) {

        RateRuleVo vo = new RateRuleVo();
        vo.setRuleSeq(StringUtils.killNull(bo.getRuleSeq()));
        vo.setRuleSeqName(StringUtils.killNull(bo.getRuleSeqName()));
        vo.setRateLevel(StringUtils.killNull(bo.getRateLevel()));
        vo.setIsOld(StringUtils.killNull(bo.getIsOld()));
        vo.setIsLcv(StringUtils.killNull(bo.getIsLcv()));

        vo.setLoanAmountMiin(StringUtils.doubleToRate(bo.getLoanAmountMiin()));
        vo.setLoanAmountMax(StringUtils.doubleToRate(bo.getLoanAmountMax()));
        vo.setInitScaleMin(StringUtils.doubleToRate(bo.getInitScaleMin()));
        vo.setInitScaleMax(StringUtils.doubleToRate(bo.getInitScaleMax()));
        vo.setrLoanPeriods(StringUtils.killNull(bo.getrLoanPeriods()));
        vo.setLoanRate(Optional.ofNullable(bo.getLoanRate()).orElse(BigDecimal.ZERO).toPlainString());
        vo.setIsAllDealer(StringUtils.killNull(bo.getIsAllDealer()));
        vo.setProSeq(StringUtils.killNull(bo.getProSeq()));
        vo.setVar(StringUtils.killNull(bo.getVar()));
        vo.setUserId(StringUtils.killNull(bo.getUserId()));
        vo.setUserName(StringUtils.killNull(bo.getUserName()));
        vo.setRemarks(StringUtils.killNull(bo.getRemarks()));
        vo.setRule1(StringUtils.killNull(bo.getRule1()));
        vo.setRule2(StringUtils.killNull(bo.getRule2()));
        vo.setRule3(StringUtils.killNull(bo.getRule3()));
        vo.setRule4(StringUtils.killNull(bo.getRule4()));
        vo.setBeginDate(bo.getBeginDate());
        vo.setEndDate(bo.getEndDate());
        vo.setHighRate(Optional.ofNullable(bo.getHighRate()).orElse(BigDecimal.ZERO).toPlainString());
        vo.setExtendProps(StringUtils.killNull(bo.getExtendProps()));
        vo.setStatus(StringUtils.killNull(bo.getStatus()));
        vo.convertEnum();
        return vo;
    }
}
