package com.mljr.heil.service.common.impl;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.base.form.DealerRefQueryForm;
import com.mljr.heil.base.form.RuleRefForm;
import com.mljr.heil.common.enums.RuleBindDealerCheckTableEnum;
import com.mljr.heil.form.DealerRuleSetForm;
import com.mljr.heil.mapper.SyDealerMapper;
import com.mljr.heil.service.common.SyDealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @Date : 上午11:23 2018/2/7
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class SyDealerServiceImpl implements SyDealerService {

    @Autowired
    private SyDealerMapper syDealerMapper;

    @Override
    public int batchDelete(DealerQueryForm form) {
        return syDealerMapper.batchDelete(form);
    }

    @Override
    public int batchInsert(DealerRuleSetForm dealerRuleSetForm) {
        return this.syDealerMapper.batchInsert(dealerRuleSetForm);
    }

    @Override
    public List<Integer> queryDealerCodeListByParam(DealerRefQueryForm form) {
        return syDealerMapper.queryDealerCodeListByParam(form);
    }

    @Override
    public List<BaseDealerRes> queryRuleDealerListByParam(RuleBindDealerCheckTableEnum tableEnum) {
        RuleRefForm form = RuleRefForm.builder().masterTable(tableEnum.getMasterTable()).slaveTable(tableEnum.getSlaveTable())
                .masterRefKey(tableEnum.getMasterRefKey()).slaveRefKey(tableEnum.getSlaveRefKey()).classify(tableEnum.getClassify()).build();
        return syDealerMapper.queryRuleDealerListByParam(form);
    }

    @Override
    public List<Integer> queryIsAllDealerRuleIdList(RuleBindDealerCheckTableEnum tableEnum) {
        RuleRefForm form = RuleRefForm.builder().masterTable(tableEnum.getMasterTable()).masterRefKey(tableEnum.getMasterRefKey()).build();
        return syDealerMapper.queryIsAllDealerRuleIdList(form);
    }

}
