package com.mljr.heil.voconvertor.product;

import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.ProductType;
import com.mljr.heil.vo.product.ProductTypeVo;
import com.mljr.heil.voconvertor.VoConvertor;
import com.mljr.util.TimeTools;

/**
 * @description: 人身保险费规则 VO转换
 * @Date : 2018/2/9 下午5:17
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class ProductTypeVoConvertor extends VoConvertor<ProductTypeVo, ProductType> {


    @Override
    public ProductTypeVo convert(ProductType bo) {
        ProductTypeVo vo = new ProductTypeVo();
        vo.setpTypeId(bo.getpTypeId());
        vo.setpClassName(StringUtils.killNull(bo.getpClassName()));
        vo.setpClassCode(StringUtils.killNull(bo.getpClassCode()));
        vo.setStatus(StringUtils.killNull(bo.getStatus()));
        vo.setpDesc(StringUtils.killNull(bo.getpDesc()));
        vo.setCreateUser(StringUtils.killNull(bo.getCreateUser()));
        vo.setCreateTime(TimeTools.format4YYYYMMDDHHMISS(bo.getCreateTime()));
        return vo;
    }
}
