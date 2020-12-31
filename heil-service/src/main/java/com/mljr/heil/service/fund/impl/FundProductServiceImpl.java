package com.mljr.heil.service.fund.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.fund.FundProduct;
import com.mljr.heil.entity.Product;
import com.mljr.heil.form.fund.FundProductForm;
import com.mljr.heil.mapper.fund.FundProductMapper;
import com.mljr.heil.service.fund.FundProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 资金方--产品 关系绑定 service
 * @Date : 2018/6/13 14:19
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Service
public class FundProductServiceImpl implements FundProductService{

    @Autowired
    FundProductMapper fundProductMapper;

    /**
     * 查询出当前资金方绑定的产品列表
     * @param form
     * @return
     */
    @Override
    public List<Product> selectBindProductByParams(PageForm<FundProductForm> form) {
        return fundProductMapper.selectBindProductByParams(form);
    }

    /**
     * 查询出当前资金方尚未绑定的产品列表
     * @param form
     * @return
     */
    @Override
    public List<Product> selectNotBindProductByParams(PageForm<FundProductForm> form) {
        return fundProductMapper.selectNotBindProductByParams(form);
    }

    /**
     * 查询出当前资金方绑定的产品个数
     * @param form
     * @return
     */
    @Override
    public int getBindProductCount(PageForm<FundProductForm> form) {
        return fundProductMapper.getBindProductCount(form);
    }

    /**
     * 查询出当前资金方尚未绑定的产品个数
     * @param form
     * @return
     */
    @Override
    public int getNotBindProductCount(PageForm<FundProductForm> form) {
        return fundProductMapper.getNotBindProductCount(form);
    }

}
