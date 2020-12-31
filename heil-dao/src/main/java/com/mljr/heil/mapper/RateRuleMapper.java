package com.mljr.heil.mapper;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.RateRule;
import com.mljr.heil.form.DealerRuleSetForm;
import com.mljr.heil.form.RateRuleForm;

import java.util.List;

public interface RateRuleMapper extends BaseMapper<RateRule,Integer,RateRuleForm> {

    /**
     * 查询未绑定启用中产品的利率规则
     * @return
     */
    List<RateRule> queryRulesUnBindProduct();

    /**
     * 查询绑定/未绑定当前门店的利率规则
     * @param pageForm
     * @return
     */
    List<RateRule> queryRateRulesForDealer(PageForm<DealerRuleSetForm> pageForm);

    int queryRateRulesForDealerCount(PageForm<DealerRuleSetForm> pageForm);

}