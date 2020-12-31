package com.mljr.heil.service.product;

import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.entity.ProductProps;
import com.mljr.heil.form.ProductPropsForm;

import java.util.List;

/**
 * @description: 产品属性Service
 * @Date : 下午2:28 2018/3/3
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public interface ProductPropsService extends BaseService<ProductProps,Long,ProductPropsForm>{
    /**
     * 根据查询参数查询集合
     * @param form
     * @return
     */
    List<ProductProps> queryList(ProductPropsForm form);

    /**
     * @description: 根据多条件删除
     * @Date : 2018/3/5 14:00
     * @Author : lihaitao
     */
    int delByForm(ProductPropsForm productPropsForm);

}
