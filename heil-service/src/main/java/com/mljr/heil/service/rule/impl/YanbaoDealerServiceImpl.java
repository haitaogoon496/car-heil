package com.mljr.heil.service.rule.impl;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.mapper.YanbaoDealerMapper;
import com.mljr.heil.service.rule.YanbaoDealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 第二/三年保险费规则 门店管理 Service
 * @Date : 下午3:35 2018/2/11
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class YanbaoDealerServiceImpl implements YanbaoDealerService {
    @Autowired
    private YanbaoDealerMapper yanbaoDealerMapper;

    @Override
    public BaseDealerRes queryRecord(BaseDealerRes primaryKey) {
        return yanbaoDealerMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(BaseDealerRes record) {
        yanbaoDealerMapper.insert(record);
        return 1;
    }

    @Override
    public int updateRecord(BaseDealerRes record) {
        return yanbaoDealerMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteRecord(BaseDealerRes primaryKey) {
        return yanbaoDealerMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public int deleteByRuleId(Integer ruleId) {
        return yanbaoDealerMapper.deleteByRuleId(ruleId);
    }

    @Override
    public int batchInsert(List<BaseDealerRes> list) {
        return yanbaoDealerMapper.batchInsert(list);
    }

    @Override
    public List<BaseDealerRes> queryList(DealerQueryForm form) {
        return yanbaoDealerMapper.queryList(form);
    }

    @Override
    public int batchInsertIgnore(List<BaseDealerRes> baseDealerResList) {
        return yanbaoDealerMapper.batchInsertIgnore(baseDealerResList);
    }

    @Override
    public int batchDelete(List<BaseDealerRes> baseDealerResList) {
        return yanbaoDealerMapper.batchDelete(baseDealerResList);
    }
}
