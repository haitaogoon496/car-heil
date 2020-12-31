package com.mljr.heil.mapper.fund;

import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.fund.PdFundRuleProp;
import com.mljr.heil.form.fund.PdFundRulePropForm;

public interface PdFundRulePropMapper extends BaseMapper<PdFundRuleProp,Integer,PdFundRulePropForm>{
    int deleteByFundRuleId(Integer fundRuleId);
}