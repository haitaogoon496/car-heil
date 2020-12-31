package com.mljr.heil.service.product.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.ProductProps;
import com.mljr.heil.form.ProductPropsForm;
import com.mljr.heil.mapper.ProductPropsMapper;
import com.mljr.heil.service.product.ProductPropsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 产品属性Service
 * @Date : 下午2:28 2018/3/3
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class ProductPropsServiceImpl implements ProductPropsService {
    @Autowired
    private ProductPropsMapper productPropsMapper;

    @Override
    public List<ProductProps> queryByPage(PageForm<ProductPropsForm> form) {
        return productPropsMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<ProductPropsForm> form) {
        return productPropsMapper.queryCount(form);
    }

    @Override
    public ProductProps queryRecord(Long primaryKey) {
        return productPropsMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(ProductProps record) {
        productPropsMapper.insert(record);
        return record.getId();
    }

    @Override
    public int updateRecord(ProductProps record) {
        return productPropsMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteRecord(Long primaryKey) {
        return productPropsMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public List<ProductProps> queryList(ProductPropsForm form) {
        return productPropsMapper.queryList(form);
    }

    @Override
    public int delByForm(ProductPropsForm productPropsForm) {
        return productPropsMapper.delByForm(productPropsForm);
    }

}
