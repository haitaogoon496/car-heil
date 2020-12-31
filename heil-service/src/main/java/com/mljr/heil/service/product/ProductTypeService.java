package com.mljr.heil.service.product;

import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.entity.ProductType;
import com.mljr.heil.form.ProductTypeForm;

import java.util.List;

/**
 * @description: 产品分类Service
 * @Date : 下午7:47 2018/3/3
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public interface ProductTypeService extends BaseService<ProductType,Integer,ProductTypeForm> {
    /**
     * 查询分类列表
     * @param form
     * @return
     */
    List<ProductType> queryList(ProductTypeForm form);
}
