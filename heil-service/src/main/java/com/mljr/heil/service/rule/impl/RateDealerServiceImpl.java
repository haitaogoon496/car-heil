package com.mljr.heil.service.rule.impl;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.mapper.RateDealerMapper;
import com.mljr.heil.service.rule.RateDealerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Date : 2018/2/8$ 14:40$
 * @Author : liht
 */
@Service
public class RateDealerServiceImpl implements RateDealerService {

    @Resource
    private RateDealerMapper rateDealerMapper;

    @Override
    public BaseDealerRes queryRecord(BaseDealerRes primaryKey) {
        BaseDealerRes res = rateDealerMapper.selectByPrimaryKey(primaryKey);
        return res;
    }

    @Override
    public int insertRecord(BaseDealerRes record) {

        return rateDealerMapper.insert(record);
    }

    @Override
    public int updateRecord(BaseDealerRes record) {
        return rateDealerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(BaseDealerRes primaryKey) {
        int count = rateDealerMapper.deleteByPrimaryKey(primaryKey);
        return count;
    }

    @Override
    public int batchInsert(List<BaseDealerRes> list) {
        return rateDealerMapper.batchInsert(list);
    }
    @Override
    public int batchInsertIgnore(List<BaseDealerRes> baseDealerResList) {
        return rateDealerMapper.batchInsertIgnore(baseDealerResList);
    }

    @Override
    public int batchDelete(List<BaseDealerRes> baseDealerResList) {
        return rateDealerMapper.batchDelete(baseDealerResList);
    }
}
