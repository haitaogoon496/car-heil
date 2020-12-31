package com.mljr.heil.service.rule.impl;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.mapper.GpsDealerMapper;
import com.mljr.heil.service.rule.GpsDealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: GPS规则适用门店配置Service
 * @Date : 下午5:42 2018/2/5
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class GpsDealerServiceImpl implements GpsDealerService {

    @Autowired
    private GpsDealerMapper gpsDealerMapper;

    @Override
    public BaseDealerRes queryRecord(BaseDealerRes primaryKey) {
        return gpsDealerMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(BaseDealerRes record) {

        return gpsDealerMapper.insert(record);
    }

    @Override
    public int updateRecord(BaseDealerRes record) {
        return gpsDealerMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteRecord(BaseDealerRes primaryKey) {
        return gpsDealerMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public List<Integer> queryDealerCodesByRuleId(Integer ruleId) {
        return gpsDealerMapper.queryDealerCodesByRuleId(ruleId);
    }

    @Override
    public int deleteByRuleId(Integer ruleId) {
        return gpsDealerMapper.deleteByRuleId(ruleId);
    }

    @Override
    public int batchInsertIgnore(List<BaseDealerRes> baseDealerResList) {
        return gpsDealerMapper.batchInsertIgnore(baseDealerResList);
    }

    @Override
    public int batchInsert(List<BaseDealerRes> list) {
        return gpsDealerMapper.batchInsert(list);
    }

    @Override
    public int batchDelete(List<BaseDealerRes> baseDealerResList) {
        return gpsDealerMapper.batchDelete(baseDealerResList);
    }
}
