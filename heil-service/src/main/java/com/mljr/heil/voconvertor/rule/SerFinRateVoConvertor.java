package com.mljr.heil.voconvertor.rule;

import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.SerFinRate;
import com.mljr.heil.vo.rule.SerFinRateVo;
import com.mljr.heil.voconvertor.VoConvertor;

/**
 * @description:
 * @Date : 2018/3/5$ 16:03$
 * @Author : liht
 */
public class SerFinRateVoConvertor extends VoConvertor<SerFinRateVo,SerFinRate> {


    @Override
    public SerFinRateVo convert(SerFinRate bo) {

        SerFinRateVo vo = new SerFinRateVo();
        vo.setId(StringUtils.killNull(bo.getId()));
        vo.setRuleSeq(StringUtils.killNull(bo.getRuleSeq()));
        vo.setBasicRate(StringUtils.bigDecimal2String(bo.getBasicRate()));
        vo.setSerFinRate(StringUtils.subZeroAndDot(StringUtils.doubleToRate(bo.getSerFinRate())));
        vo.setSerFinRebateRate(StringUtils.bigDecimal2String(bo.getSerFinRebateRate()));
        vo.setHighRate(null == bo.getHighRate() ? "" : bo.getHighRate().toString());// 高风险融费率 可以为空，但不能为空作为0.00处理
        vo.setExtendProps(StringUtils.killNull(bo.getExtendProps()));
        return vo;
    }

}
