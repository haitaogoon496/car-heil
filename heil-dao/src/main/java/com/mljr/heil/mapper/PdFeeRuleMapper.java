package com.mljr.heil.mapper;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.PdFeeRule;
import com.mljr.heil.form.DealerRuleSetForm;
import com.mljr.heil.form.PdFeeRuleForm;
import com.mljr.heil.form.RuleStatusForm;

import java.util.List;

public interface PdFeeRuleMapper extends BaseMapper<PdFeeRule,Integer,PdFeeRuleForm> {

    /**
     * 查询绑定/未绑定当前门店的Pd（人身，续保）规则
     * @param pageForm
     * @return
     */
    List<PdFeeRule> queryPdFeeRulesForDealer(PageForm<DealerRuleSetForm> pageForm);

    int queryPdFeeRulesForDealerCount(PageForm<DealerRuleSetForm> pageForm);

    /**
     * 查询没有绑定产品的续保押金
     * @param classify
     * @return
     */
    List<PdFeeRule> queryRulesUnBindProduct(Integer classify);

    /**
     * 修改规则状态
     * @param record
     * @return
     */
    int modifyStatus(RuleStatusForm record);
}