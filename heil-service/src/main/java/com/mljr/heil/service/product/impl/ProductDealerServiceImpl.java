package com.mljr.heil.service.product.impl;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.mapper.ProductDealerMapper;
import com.mljr.heil.service.product.ProductDealerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 产品门店管理
 * @Date : 2018/2/8$ 14:40$
 * @Author : liht
 */
@Service
public class ProductDealerServiceImpl implements ProductDealerService {

    @Resource
    private ProductDealerMapper productDealerMapper;

    @Override
    public int insertRecord(BaseDealerRes record) {

        return productDealerMapper.insert(record);
    }

    @Override
    public int updateRecord(BaseDealerRes record) {
        return productDealerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(BaseDealerRes primaryKey) {
        int count = productDealerMapper.deleteByPrimaryKey(primaryKey);
        return count;
    }

    @Override
    public int batchInsert(List<BaseDealerRes> list) {
        return productDealerMapper.batchInsert(list);
    }

    @Override
    public int batchInsertIgnore(List<BaseDealerRes> baseDealerResList) {
        return productDealerMapper.batchInsertIgnore(baseDealerResList);
    }
    @Override
    public int batchDelete(List<BaseDealerRes> baseDealerResList) {
        return productDealerMapper.batchDelete(baseDealerResList);
    }

    @Override
    public int deleteProductBindAllDealer(Integer id) {
        return productDealerMapper.deleteProductBindAllDealer(id);
    }

    @Override
    public List<BaseDealerRes> queryList(DealerQueryForm form) {
        return productDealerMapper.queryList(form);
    }
}
