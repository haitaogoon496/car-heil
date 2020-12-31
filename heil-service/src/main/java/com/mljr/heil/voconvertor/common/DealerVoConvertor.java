package com.mljr.heil.voconvertor.common;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.vo.common.DealerVo;
import com.mljr.heil.voconvertor.VoConvertor;

/**
 * @description:
 * @Date : 2018/2/8$ 15:55$
 * @Author : liht
 */
public class DealerVoConvertor extends VoConvertor<DealerVo , BaseDealerRes> {
    @Override
    public DealerVo convert(BaseDealerRes bo) {

        DealerVo vo = new DealerVo();
        vo.setId(StringUtils.killNull(bo.getId()));
        vo.setRuleSeq(StringUtils.killNull(bo.getRuleSeq()));
        vo.setDealerCode(StringUtils.killNull(bo.getDealerCode()));
        vo.setDealerName(StringUtils.killNull(bo.getDealerName()));
        vo.setCompanyName(StringUtils.killNull(bo.getCompanyName()));
        vo.setLinked(StringUtils.killNull(bo.getLinked()));

        return vo;
    }
}
