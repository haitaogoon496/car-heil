package com.mljr.heil.service.rule;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.base.service.BaseService;

/**
 * @description: 第二/三年保险费规则 门店管理 Service
 * @Date : 下午3:33 2018/2/11
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public interface YanbaoDealerService extends BaseService<BaseDealerRes,BaseDealerRes,DealerQueryForm> {

    /**
     * 根据规则id删除相关记录
     * @param ruleId 关联主键id
     * @return
     */
    int deleteByRuleId(Integer ruleId);
}
