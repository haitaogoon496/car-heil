package com.mljr.heil.service.product.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.Product;
import com.mljr.heil.form.ProductForm;
import com.mljr.heil.mapper.ProductMapper;
import com.mljr.heil.service.product.ProductService;
import com.mljr.heil.vo.common.ModifyStatusVo;
import com.mljr.util.TimeTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 车贷产品 Service
 * @Date : 下午6:14 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<Product> queryByPage(PageForm<ProductForm> form) {
        return productMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<ProductForm> form) {
        return productMapper.queryCount(form);
    }

    @Override
    public Product queryRecord(Integer primaryKey) {
        return productMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(Product record) {
        productMapper.insertSelective(record);
        return record.getpId();
    }

    @Override
    public int updateRecord(Product record) {
        return productMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return productMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public int modifyStatus(ModifyStatusVo param) {
        Product product = new Product();
        product.setpId(param.getProductId());
        product.setStatus(param.getStatus());
        product.setLastUpdate(TimeTools.createNowTime());
        return productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public List<Product> queryProductNotSerFin() {
        return productMapper.queryProductNotSerFin();
    }

    @Override
    public List<Product> queryProductNotGpsOrRate(String classify) {
        return productMapper.queryProductNotGpsOrRate(classify);
    }

    @Override
    public List<Product> queryList(ProductForm form) {
        return productMapper.queryList(form);
    }
}
