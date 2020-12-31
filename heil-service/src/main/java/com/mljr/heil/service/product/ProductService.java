package com.mljr.heil.service.product;

import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.entity.Product;
import com.mljr.heil.form.ProductForm;
import com.mljr.heil.vo.common.ModifyStatusVo;

import java.util.List;

/**
 * @description: 车贷产品 Service
 * @Date : 2018/2/27 下午6:24
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface ProductService extends BaseService<Product,Integer,ProductForm> {
    /**
     * 产品状态更新
     * @param param
     * @return
     */
    int modifyStatus(ModifyStatusVo param);

    /**
     * 查询没有平台费的启用中产品
     * @return
     */
    List<Product> queryProductNotSerFin();

    /**
     * 查询未绑定gps和利率的产品
     * @param classify
     * @return
     */
    List<Product> queryProductNotGpsOrRate(String classify);

}
