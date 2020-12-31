package com.mljr.heil.mapper.fund;


import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.Product;
import com.mljr.heil.entity.fund.Fund;
import com.mljr.heil.form.fund.FundForm;
import com.mljr.heil.form.fund.FundProductForm;

import java.util.List;

/**
 * @description:  资金方实体类
 * @Date : 2018/6/12 17:11
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public interface FundMapper extends BaseMapper<Fund,Integer,FundForm> {
    /**
     * 分页查询出当前资金方绑定的产品信息
     * @param form
     * @return
     */
    List<Product> selectBindProductByParams(PageForm<FundForm> form);

    /**
     * 查询出当前资金方绑定的产品个数
     * @param form
     * @return
     */
    int getBindProductCount(PageForm<FundForm> form);

    /**
     * 分页查询出当前资金方尚未绑定的产品列表
     * @param form
     * @return
     */
    List<Product> selectNotBindProductByParams(PageForm<FundForm> form);

    /**
     * 查询出当前资金方尚未绑定的产品个数
     * @param form
     * @return
     */
    int getNotBindProductCount(PageForm<FundForm> form);
}
