package com.mljr.heil.mapper;

import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.PdFeeDealer;
import com.mljr.heil.form.PdFeeDealerForm;

public interface PdFeeDealerMapper extends BaseMapper<PdFeeDealer,Integer,PdFeeDealerForm> {
    /**
     * 根据规则id删除相关记录
     * @param ruleId 关联主键id
     * @return
     */
    int deleteByRuleId(Integer ruleId);

    int batchDelete(PdFeeDealerForm form);
}