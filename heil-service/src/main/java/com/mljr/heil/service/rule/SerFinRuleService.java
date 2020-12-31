package com.mljr.heil.service.rule;

import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.entity.SerFinRule;
import com.mljr.heil.form.SerFinRuleForm;

import java.util.List;

/**
 * @description: 平台费规则配置
 * @Date : 2018/2/11$ 16:58$
 * @Author : liht
 */
public interface SerFinRuleService extends BaseService<SerFinRule,Integer,SerFinRuleForm>{

    /**
     * 根据产品id查询所属平台费规则
     * @return
     */
    List<SerFinRule> queryRulesByProductId(Integer pId);

    /**
     * 查询没有绑定产品的平台费
     * @return
     */
    List<SerFinRule> queryRulesUnBindProduct();
}
