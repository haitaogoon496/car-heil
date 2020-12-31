package com.mljr.heil.mapper;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.Product;
import com.mljr.heil.form.DealerRuleSetForm;
import com.mljr.heil.form.ProductForm;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product,Integer,ProductForm> {
    /**
     * 查询没有绑定平台费的产品
     * @return
     */
    List<Product> queryProductNotSerFin();

    int bindProductForFund(ProductForm product);

    /**
     * 查询绑定/未绑定当前门店的产品
     * @param pageForm
     * @return
     */
    List<Product> queryProductsForDealer(PageForm<DealerRuleSetForm> pageForm);

    int queryProductsForDealerCount(PageForm<DealerRuleSetForm> pageForm);
    /**
     * @description:查询未绑定gps和利率规则的产品
     * @Date : 2018/9/20 14:23
     * @Author : lihaitao
     */
    List<Product> queryProductNotGpsOrRate(String classify);

}