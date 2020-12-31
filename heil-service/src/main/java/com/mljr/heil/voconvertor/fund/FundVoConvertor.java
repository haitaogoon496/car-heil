package com.mljr.heil.voconvertor.fund;

import com.mljr.heil.entity.fund.Fund;
import com.mljr.heil.vo.fund.FundVo;
import com.mljr.heil.voconvertor.VoConvertor;

/**
 * @description: 资金方信息 convertor
 * @Date : 2018/6/12 19:10
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public class FundVoConvertor  extends VoConvertor<FundVo, Fund> {
    @Override
    public FundVo convert(Fund bo) {
        FundVo vo = new FundVo();
        vo.setId(bo.getId());
        vo.setFundNo(bo.getFundNo());
        vo.setFundName(bo.getFundName());
        vo.setRemark(bo.getRemark());
        vo.setStatus(bo.getStatus());
        vo.setCreateTime(bo.getCreateTime());
        vo.setUpdateTime(bo.getUpdateTime());
        return vo;
    }
}
