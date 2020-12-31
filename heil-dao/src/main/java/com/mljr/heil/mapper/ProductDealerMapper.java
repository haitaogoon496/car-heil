package com.mljr.heil.mapper;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.base.mapper.BaseMapper;

import java.util.List;

public interface ProductDealerMapper extends BaseMapper<BaseDealerRes,BaseDealerRes,DealerQueryForm>{
    /**
     * 批量插入(没有即插入，有的话不做处理)
     * @param baseDealerResList
     * @return
     */
    int batchInsertIgnore(List<BaseDealerRes> baseDealerResList);

    /**
     * 删除该产品绑定的所有门店
     * @param id
     * @return
     */
    int deleteProductBindAllDealer(Integer id);
}