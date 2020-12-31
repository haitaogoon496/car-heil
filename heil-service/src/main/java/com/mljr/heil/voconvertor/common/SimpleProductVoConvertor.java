package com.mljr.heil.voconvertor.common;

import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.Product;
import com.mljr.heil.vo.product.SimpleProductVo;
import com.mljr.heil.voconvertor.VoConvertor;

/**
 * @description:
 * @Date : 2018/4/3$ 16:05$
 * @Author : liht
 */
public class SimpleProductVoConvertor extends VoConvertor<SimpleProductVo,Product> {
    @Override
    public SimpleProductVo convert(Product bo) {
        SimpleProductVo vo = new SimpleProductVo();
        vo.setProductCode(StringUtils.killNull(bo.getProductCoe()));
        vo.setProductName(StringUtils.killNull(bo.getpName()));
        vo.setStatusDesc(StringUtils.killNull(bo.getDesp()));
        vo.setType(StringUtils.killNull(bo.getType()));
        vo.setpId(StringUtils.killNull(bo.getpId()));
        return vo;
    }
}
