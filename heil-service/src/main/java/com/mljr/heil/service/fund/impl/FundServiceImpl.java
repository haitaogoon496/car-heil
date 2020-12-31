package com.mljr.heil.service.fund.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.Product;
import com.mljr.heil.entity.fund.Fund;
import com.mljr.heil.form.fund.FundForm;
import com.mljr.heil.form.fund.FundProductForm;
import com.mljr.heil.mapper.fund.FundMapper;
import com.mljr.heil.service.fund.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 资金方表操作 service
 * @Date : 2018/6/12 18:34
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Service
public class FundServiceImpl implements FundService{

    @Autowired
    FundMapper fundMapper;

    /**
     * 查询出当前资金方绑定的产品列表
     * @param form
     * @return
     */
    @Override
    public List<Product> selectBindProductByParams(PageForm<FundForm> form) {
        return fundMapper.selectBindProductByParams(form);
    }

    /**
     * 查询出当前资金方尚未绑定的产品列表
     * @param form
     * @return
     */
    @Override
    public List<Product> selectNotBindProductByParams(PageForm<FundForm> form) {
        return fundMapper.selectNotBindProductByParams(form);
    }

    /**
     * 查询出当前资金方绑定的产品个数
     * @param form
     * @return
     */
    @Override
    public int getBindProductCount(PageForm<FundForm> form) {
        return fundMapper.getBindProductCount(form);
    }

    /**
     * 查询出当前资金方尚未绑定的产品个数
     * @param form
     * @return
     */
    @Override
    public int getNotBindProductCount(PageForm<FundForm> form) {
        return fundMapper.getNotBindProductCount(form);
    }

    @Override
    public List<Fund> queryByPage(PageForm<FundForm> form) {
        return fundMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<FundForm> form) {
        return fundMapper.queryCount(form);
    }

    @Override
    public Fund queryRecord(Integer primaryKey) {
        return fundMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(Fund record) {
        return fundMapper.insert(record);
    }

    @Override
    public int updateRecord(Fund record) {
        return fundMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return fundMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public int updateByPrimaryKeySelective(Fund record) {
        return fundMapper.updateByPrimaryKeySelective(record);
    }

}
