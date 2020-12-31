package com.mljr.heil.voconvertor.product;

import com.lyqc.base.enums.ProductConstant;
import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.Product;
import com.mljr.heil.vo.product.ProductVo;
import com.mljr.heil.voconvertor.VoConvertor;
import com.mljr.util.TimeTools;

import java.util.Optional;

/**
 * @description: 车贷产品 VO转换
 * @Date : 2018/2/27 下午6:24
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class ProductVoConvertor extends VoConvertor<ProductVo, Product> {

    @Override
    public ProductVo convert(Product bo) {
        ProductVo vo = new ProductVo();
        vo.setPId(bo.getpId());
        vo.setYear(StringUtils.killNull(bo.getYear()));
        vo.setProductCode(StringUtils.killNull(bo.getProductCoe()));
        vo.setProductName(StringUtils.killNull(bo.getpName()));
        vo.setType(bo.getType());
        vo.setStatus(bo.getStatus());
        vo.setStatusDesc(ProductConstant.ProductStatusEnum.getNameByIndex(Optional.ofNullable(bo.getStatus()).orElse(ProductConstant.ProductStatusEnum.ENABLE.getIndex())));
        vo.setVer(bo.getVer());
        vo.setDesp(StringUtils.killNull(bo.getDesp()));
        vo.setIsAllDealer(StringUtils.killNull(bo.getIsAllDealer()));
        vo.setIsBrand(StringUtils.killNull(bo.getIsBrand()));
        vo.setIsSeries(StringUtils.killNull(bo.getIsSeries()));
        vo.setIsStyles(StringUtils.killNull(bo.getIsStyles()));
        vo.setCarry(StringUtils.killNull(bo.getCarry()));
        vo.setPrecisions(bo.getPrecisions());
        vo.setCreateTime(StringUtils.killNull(TimeTools.format4YYYYMMDD(bo.getCreateTime())));
        vo.setLastUpdate(StringUtils.killNull(TimeTools.format4YYYYMMDD(bo.getLastUpdate())));
        vo.setBeginTime(StringUtils.killNull(TimeTools.format4YYYYMMDD(bo.getBeginTime())));
        vo.setEndTime(StringUtils.killNull(TimeTools.format4YYYYMMDD(bo.getEndTime())));
        vo.setUserId(StringUtils.killNull(bo.getUserId()));
        vo.setUserName(StringUtils.killNull(bo.getUserName()));
        vo.setTypeName(StringUtils.killNull(bo.getTypeName()));
        vo.setFundName(StringUtils.killNull(bo.getFundName()));
        return vo;
    }
}
