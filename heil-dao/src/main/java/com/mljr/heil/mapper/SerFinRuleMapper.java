package com.mljr.heil.mapper;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.SerFinRule;
import com.mljr.heil.form.DealerRuleSetForm;
import com.mljr.heil.form.SerFinRuleForm;

import java.util.List;

public interface SerFinRuleMapper extends BaseMapper<SerFinRule,Integer,SerFinRuleForm>{
    /**
     * 查询绑定当前门店的平台费规则
     * @param pageForm
     * @return
     */
    List<SerFinRule> querySerRulesForDealer(PageForm<DealerRuleSetForm> pageForm);

    int querySerRulesForDealerCount(PageForm<DealerRuleSetForm> pageForm);

    /**
     * 根据产品id查询平台费列表
     * @param pId
     * @return
     */
    List<SerFinRule> queryRulesByProductId(Integer pId);

    /**
     * 查询没有绑定产品的平台费
     * @return
     */
    List<SerFinRule> queryRulesUnBindProduct();

}