package com.mljr.heil.voconvertor.rule;

import com.google.common.base.Strings;
import com.mljr.heil.common.enums.YesOrNoEnum;
import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.PdFeeDealer;
import com.mljr.heil.vo.common.DealerVo;
import com.mljr.heil.voconvertor.VoConvertor;

/**
 * @description:
 * @Date : 2018/2/8$ 15:55$
 * @Author : liht
 */
public class PdFeeDealerVoConvertor extends VoConvertor<DealerVo,PdFeeDealer> {
    @Override
    public DealerVo convert(PdFeeDealer bo) {
        DealerVo vo = new DealerVo();
        vo.setId(StringUtils.killNull(bo.getId()));
        vo.setRuleSeq(StringUtils.killNull(bo.getId()));
        vo.setDealerCode(StringUtils.killNull(bo.getDealerCode()));
        vo.setDealerName(StringUtils.killNull(bo.getDealerName()));
        vo.setCompanyName(StringUtils.killNull(bo.getCompanyName()));
        vo.setLinked(StringUtils.killNull(Strings.isNullOrEmpty(bo.getRemarks()) ?
                YesOrNoEnum.YES.getIndex() : YesOrNoEnum.NO.getIndex()));
        return vo;
    }
}
