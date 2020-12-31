package com.mljr.heil.mapper;

import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.ProductProps;
import com.mljr.heil.form.ProductPropsForm;
/**
 * @description: 产品属性 Mapper
 * @Date : 2018/3/3 下午2:31
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface ProductPropsMapper extends BaseMapper<ProductProps,Long,ProductPropsForm> {

    /**
     * @description: 根据多条件删除
     * @Date : 2018/3/5 14:03
     * @Author : lihaitao
     */
    int delByForm(ProductPropsForm productPropsForm);
}