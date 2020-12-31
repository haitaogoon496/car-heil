package com.mljr.heil.service.fund;

import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.entity.fund.PdFundRuleProp;
import com.mljr.heil.form.fund.PdFundRulePropForm;

import java.util.List;

/**
 * @description: 资金方规则属性service
 * @Date : 2018/6/13 11:04
 * @Author : lihaitao
 */
public interface PdFundRulePropService extends BaseService<PdFundRuleProp, Integer, PdFundRulePropForm> {
    int deleteByFundRuleId(Integer fundRuleId);
}
