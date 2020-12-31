package com.mljr.heil.mapper.fund;


import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.fund.FundProduct;
import com.mljr.heil.entity.Product;
import com.mljr.heil.form.fund.FundProductForm;

import java.util.List;

/**
 * @description:  资金方--产品关系绑定表
 * @Date : 2018/6/12 20:28
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public interface FundProductMapper extends BaseMapper<FundProduct,Integer,FundProductForm> {

    /**
     * 分页查询出当前资金方绑定的产品信息
     * @param form
     * @return
     */
    List<Product> selectBindProductByParams(PageForm<FundProductForm> form);

    /**
     * 查询出当前资金方绑定的产品个数
     * @param form
     * @return
     */
    int getBindProductCount(PageForm<FundProductForm> form);

    /**
     * 分页查询出当前资金方尚未绑定的产品列表
     * @param form
     * @return
     */
    List<Product> selectNotBindProductByParams(PageForm<FundProductForm> form);

    /**
     * 查询出当前资金方尚未绑定的产品个数
     * @param form
     * @return
     */
    int getNotBindProductCount(PageForm<FundProductForm> form);
}
