package com.mljr.heil.service.rule.impl;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.mapper.AccountDealerMapper;
import com.mljr.heil.service.rule.AccountDealerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 账号管理费门店配置
 * @Date : 2018/2/8$ 14:40$
 * @Author : liht
 */
@Service
public class AccountDealerServiceImpl implements AccountDealerService {

    @Resource
    private AccountDealerMapper accountDealerMapper;

    @Override
    public BaseDealerRes queryRecord(BaseDealerRes primaryKey) {
        BaseDealerRes res = accountDealerMapper.selectByPrimaryKey(primaryKey);
        return res;
    }

    @Override
    public int insertRecord(BaseDealerRes record) {

        return accountDealerMapper.insert(record);
    }

    @Override
    public int updateRecord(BaseDealerRes record) {
        return accountDealerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(BaseDealerRes primaryKey) {
        int count = accountDealerMapper.deleteByPrimaryKey(primaryKey);
        return count;
    }

    @Override
    public int batchInsert(List<BaseDealerRes> list) {
        return accountDealerMapper.batchInsert(list);
    }

    @Override
    public int batchInsertIgnore(List<BaseDealerRes> baseDealerResList) {
        return accountDealerMapper.batchInsertIgnore(baseDealerResList);
    }

    @Override
    public int batchDelete(List<BaseDealerRes> baseDealerResList) {
        return accountDealerMapper.batchDelete(baseDealerResList);
    }
}
