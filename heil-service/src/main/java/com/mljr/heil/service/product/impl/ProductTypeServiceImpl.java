package com.mljr.heil.service.product.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.ProductType;
import com.mljr.heil.form.ProductTypeForm;
import com.mljr.heil.mapper.ProductTypeMapper;
import com.mljr.heil.service.product.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 产品分类Service
 * @Date : 下午7:48 2018/3/3
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;
    @Override
    public List<ProductType> queryByPage(PageForm<ProductTypeForm> form) {
        return productTypeMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<ProductTypeForm> form) {
        return productTypeMapper.queryCount(form);
    }

    @Override
    public ProductType queryRecord(Integer primaryKey) {
        return productTypeMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(ProductType record) {
        productTypeMapper.insert(record);
        return record.getpTypeId();
    }

    @Override
    public int updateRecord(ProductType record) {
        return productTypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return productTypeMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public List<ProductType> queryList(ProductTypeForm form) {
        return productTypeMapper.queryList(form);
    }
}
