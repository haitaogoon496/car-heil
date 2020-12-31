package com.mljr.heil.service.product;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.base.service.BaseService;

/**
 * @description:
 * @Date : 2018/2/12$ 14:27$
 * @Author : liht
 */
public interface ProductDealerService extends BaseService<BaseDealerRes,BaseDealerRes,DealerQueryForm> {

    /**
     * 删除该产品绑定的所有门店
     * @param id
     * @return
     */
    int deleteProductBindAllDealer(Integer id);
}
