package com.mljr.heil.voconvertor.common;

import com.lyqc.transfer.re.DealerRe;
import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.vo.common.SyDealerVo;
import com.mljr.heil.voconvertor.VoConvertor;
import org.springframework.beans.BeanUtils;

/**
 * @author lingyu.shang
 */
public class DealerReVoConvertor extends VoConvertor<SyDealerVo, DealerRe> {
    @Override
    public SyDealerVo convert(DealerRe bo) {
        SyDealerVo vo = new SyDealerVo();
        BeanUtils.copyProperties(bo, vo);
        vo.setDealerCode(StringUtils.killNull(bo.getDealerCode()));
        return vo;
    }
}
