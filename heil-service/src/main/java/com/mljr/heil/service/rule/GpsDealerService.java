package com.mljr.heil.service.rule;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.base.service.BaseService;

import java.util.List;

/**
 * @description: GPS规则适用门店配置Service
 * @Date : 下午5:37 2018/2/5
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public interface GpsDealerService extends BaseService<BaseDealerRes,BaseDealerRes,DealerQueryForm> {
    /**
     * 根据主表规则id查询相应配置门店
     * @param ruleId 规则id
     * @return 门店code列表集合
     */
    List<Integer> queryDealerCodesByRuleId(Integer ruleId);
    /**
     * 根据规则id删除相关记录
     * @param ruleId 关联主键id
     * @return
     */
    int deleteByRuleId(Integer ruleId);

}
