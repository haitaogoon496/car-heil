package com.mljr.heil.voconvertor.common;

import com.lyqc.transfer.re.DealerRe;
import com.mljr.heil.vo.common.DealerVo;
import com.mljr.heil.voconvertor.VoConvertor;
import org.springframework.beans.BeanUtils;

/**
 * @author lingyu.shang
 */
public class DealerRefConvertor  extends VoConvertor<DealerVo, DealerRe> {

    @Override
    public DealerVo convert(DealerRe bo) {
        DealerVo vo = new DealerVo();
        BeanUtils.copyProperties(bo, vo);
        vo.setDealerCode(bo.getDealerCode().toString());
        return vo;
    }

}
