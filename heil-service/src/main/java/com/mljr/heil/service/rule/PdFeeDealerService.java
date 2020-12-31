package com.mljr.heil.service.rule;

import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.entity.PdFeeDealer;
import com.mljr.heil.form.PdFeeDealerForm;

/**
 * @description: 人身保险费规则 适用门店配置Service
 * @Date : 下午5:37 2018/2/5
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public interface PdFeeDealerService extends BaseService<PdFeeDealer,Integer,PdFeeDealerForm> {
    /**
     * 根据规则id删除相关记录
     * @param ruleId 关联主键id
     * @return
     */
    int deleteByRuleId(Integer ruleId);

    int batchDelete(PdFeeDealerForm form);
}
