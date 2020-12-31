package com.mljr.heil.service.rule.impl;

import com.mljr.heil.entity.PdFeeDealer;
import com.mljr.heil.form.PdFeeDealerForm;
import com.mljr.heil.mapper.PdFeeDealerMapper;
import com.mljr.heil.service.rule.PdFeeDealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 人身保险费规则  适用门店配置Service
 * @Date : 下午5:42 2018/2/5
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class PdFeeDealerServiceImpl implements PdFeeDealerService {

    @Autowired
    private PdFeeDealerMapper pdFeeDealerMapper;

    @Override
    public PdFeeDealer queryRecord(Integer primaryKey) {
        return pdFeeDealerMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(PdFeeDealer record) {
        pdFeeDealerMapper.insert(record);
        return record.getId();
    }

    @Override
    public int updateRecord(PdFeeDealer record) {
        return pdFeeDealerMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return pdFeeDealerMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public int deleteByRuleId(Integer ruleId) {
        return pdFeeDealerMapper.deleteByRuleId(ruleId);
    }

    @Override
    public int batchInsert(List<PdFeeDealer> list) {
        return pdFeeDealerMapper.batchInsert(list);
    }

    @Override
    public int batchDelete(PdFeeDealerForm form){
        return pdFeeDealerMapper.batchDelete(form);
    }

    @Override
    public int batchInsertIgnore(List<PdFeeDealer> baseDealerResList) {
        return pdFeeDealerMapper.batchInsertIgnore(baseDealerResList);
    }
}
