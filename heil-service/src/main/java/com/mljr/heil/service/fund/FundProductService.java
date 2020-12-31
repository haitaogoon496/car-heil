package com.mljr.heil.service.fund;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.entity.fund.FundProduct;
import com.mljr.heil.entity.Product;
import com.mljr.heil.form.fund.FundProductForm;

import java.util.List;

/**
 * @description: 资金方--产品绑定表
 * @Date : 2018/6/13 14:16
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public interface FundProductService extends BaseService<FundProduct,Integer,FundProductForm>{

    /**
     * 查询出当前资金方绑定的产品信息
     * @param form
     * @return
     */
     List<Product> selectBindProductByParams(PageForm<FundProductForm> form);

    /**
     * 查询出当前资金方尚未绑定的产品列表
     * @param form
     * @return
     */
     List<Product> selectNotBindProductByParams(PageForm<FundProductForm> form);

    /**
     * 查询出当前资金方绑定的产品个数
     * @param form
     * @return
     */
     int getBindProductCount(PageForm<FundProductForm> form);

    /**
     * 查询出当前资金方尚未绑定的产品个数
     * @param form
     * @return
     */
     int getNotBindProductCount(PageForm<FundProductForm> form);
}
