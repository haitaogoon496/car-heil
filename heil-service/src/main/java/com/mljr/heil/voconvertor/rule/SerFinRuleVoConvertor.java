package com.mljr.heil.voconvertor.rule;

import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.SerFinRule;
import com.mljr.heil.vo.rule.SerFinRuleVo;
import com.mljr.heil.voconvertor.VoConvertor;

/**
 * @description:
 * @Date : 2018/2/7$ 13:55$
 * @Author : liht
 */
public class SerFinRuleVoConvertor extends VoConvertor<SerFinRuleVo, SerFinRule> {


    @Override
    public SerFinRuleVo convert(SerFinRule bo) {

        SerFinRuleVo vo = new SerFinRuleVo();
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
        vo.setVar(StringUtils.killNull(bo.getVar()));
        vo.setUserId(StringUtils.killNull(bo.getUserId()));
        vo.setUserName(StringUtils.killNull(bo.getUserName()));
        vo.setRemarks(StringUtils.killNull(bo.getRemarks()));
        vo.setRule1(StringUtils.killNull(bo.getRule1()));
        vo.setRule2(StringUtils.killNull(bo.getRule2()));
        vo.setRule3(StringUtils.killNull(bo.getRule3()));
        vo.setRule4(StringUtils.killNull(bo.getRule4()));
        vo.setWorkFlow(StringUtils.killNull(bo.getWorkFlow()));
        vo.setProducts(StringUtils.killNull(bo.getProducts()));
        vo.setCommFloatFeeEnable(bo.getCommFloatFeeEnable());
        vo.setCommFloatFeeRateMax(StringUtils.bigDecimal2String(bo.getCommFloatFeeRateMax()));
        vo.setCommFloatFeeRateMin(StringUtils.bigDecimal2String(bo.getCommFloatFeeRateMin()));
        vo.setBeginDate(bo.getBeginDate());
        vo.setEndDate(bo.getEndDate());
        vo.setTakenMode(StringUtils.killNull(bo.getTakenMode()));
        vo.setStatus(StringUtils.killNull(bo.getStatus()));
        vo.convertEnum();
        return vo;
    }
}
