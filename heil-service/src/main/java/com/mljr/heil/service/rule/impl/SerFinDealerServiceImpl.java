package com.mljr.heil.service.rule.impl;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.form.SerFinDealerForm;
import com.mljr.heil.mapper.SerFinDealerMapper;
import com.mljr.heil.service.rule.SerFinDealerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Date : 2018/2/8$ 14:40$
 * @Author : liht
 */
@Service
public class SerFinDealerServiceImpl implements SerFinDealerService {

    @Resource
    private SerFinDealerMapper serFinDealerMapper;

    @Override
    public BaseDealerRes queryRecord(BaseDealerRes primaryKey) {
        BaseDealerRes res = serFinDealerMapper.selectByPrimaryKey(primaryKey);
        return res;
    }

    @Override
    public int insertRecord(BaseDealerRes record) {

        return serFinDealerMapper.insert(record);
    }

    @Override
    public int updateRecord(BaseDealerRes record) {
        return serFinDealerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(BaseDealerRes primaryKey) {
        int count = serFinDealerMapper.deleteByPrimaryKey(primaryKey);
        return count;
    }

    @Override
    public int deleteByParams(SerFinDealerForm form) {
        return serFinDealerMapper.deleteByParams(form);
    }

    @Override
    public int batchInsert(List<BaseDealerRes> list) {
        return serFinDealerMapper.batchInsert(list);
    }
    @Override
    public int batchInsertIgnore(List<BaseDealerRes> baseDealerResList) {
        return serFinDealerMapper.batchInsertIgnore(baseDealerResList);
    }

    @Override
    public int batchDelete(List<BaseDealerRes> baseDealerResList) {
        return serFinDealerMapper.batchDelete(baseDealerResList);
    }
}
