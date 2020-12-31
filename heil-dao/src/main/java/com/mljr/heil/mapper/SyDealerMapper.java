package com.mljr.heil.mapper;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.base.form.DealerRefQueryForm;
import com.mljr.heil.base.form.RuleRefForm;
import com.mljr.heil.form.DealerRuleSetForm;

import java.util.List;

/**
 * @description: 门店Mapper
 * @Date : 2018/2/7 上午11:22
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface SyDealerMapper {

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
     * 根据查询关系表，根据指定 id，查询关联的门店Code列表，使用 ${}，值必须从 {@link com.mljr.heil.common.enums.ApplyDealerTableEnum} 枚举获取
     * @param form
     * @return
     */
    List<Integer> queryDealerCodeListByParam(DealerRefQueryForm form);

    /**
     * 根据条件查询规则门店关系列表，使用 ${}，值必须从 {@link com.mljr.heil.common.enums.RuleBindDealerCheckTableEnum} 枚举获取
     * @param ruleDealerRefForm
     * @return
     */
    List<BaseDealerRes> queryRuleDealerListByParam(RuleRefForm ruleDealerRefForm);


    /**
     * 查询适用于所有门店的规则ID列表，使用 ${}，值必须从 {@link com.mljr.heil.common.enums.RuleBindDealerCheckTableEnum} 枚举获取
     * @param form
     * @return
     */
    List<Integer> queryIsAllDealerRuleIdList(RuleRefForm form);

}