package com.mljr.heil.service.rule;

import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.entity.PdFeeRule;
import com.mljr.heil.form.PdFeeRuleForm;
import com.mljr.heil.form.RuleStatusForm;

import java.util.List;

/**
 * @description: 人身保险费规则 Service
 * @Date : 下午5:47 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public interface PdFeeRuleService extends BaseService<PdFeeRule,Integer,PdFeeRuleForm> {

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
