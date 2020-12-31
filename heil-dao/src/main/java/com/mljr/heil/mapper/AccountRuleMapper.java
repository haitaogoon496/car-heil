package com.mljr.heil.mapper;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.AccountRule;
import com.mljr.heil.form.AccountRuleForm;
import com.mljr.heil.form.DealerRuleSetForm;

import java.util.List;


public interface AccountRuleMapper extends BaseMapper<AccountRule,Integer, AccountRuleForm>{
    /**
     * 查询绑定/未绑定该门店的账户管理费规则
     *
     * @param pageForm
     * @return
     */
    List<AccountRule> queryAccountRulesForDealer(PageForm<DealerRuleSetForm> pageForm);

    int queryAccountRulesForDealerCount(PageForm<DealerRuleSetForm> pageForm);

}