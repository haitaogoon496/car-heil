package com.mljr.heil.service.common;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.base.form.DealerRefQueryForm;
import com.mljr.heil.common.enums.RuleBindDealerCheckTableEnum;
import com.mljr.heil.form.DealerRuleSetForm;

import java.util.List;

/**
 * @description: 门店Service接口
 * @Date : 上午11:23 2018/2/7
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public interface SyDealerService {

    /**
     * 批量删除
     * 业务场景：根据ruleId、dealerScopes、tableName 处理
     * @param form
     * @return
     */
    int batchDelete(DealerQueryForm form);

    /**
     * 为门店批量设置规则
     * @param dealerRuleSetForm
     * @return
     */
    int batchInsert(DealerRuleSetForm dealerRuleSetForm);

    /**
     * 查询门店code列表
     * @param form 参数来源 {@link com.mljr.heil.common.enums.ApplyDealerTableEnum}
     * @return
     */
    List<Integer> queryDealerCodeListByParam(DealerRefQueryForm form);

    /**
     * 根据条件查询规则门店关系列表
     * @param tableEnum
     * @return
     */
    List<BaseDealerRes> queryRuleDealerListByParam(RuleBindDealerCheckTableEnum tableEnum);

    /**
     * 查询指定表适用于所有门店的规则ID列表
     * @param tableEnum
     * @return
     */
    List<Integer> queryIsAllDealerRuleIdList(RuleBindDealerCheckTableEnum tableEnum);

}
