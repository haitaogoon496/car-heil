package com.mljr.heil.service.rule;

import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.entity.GpsRule;
import com.mljr.heil.form.GpsRuleForm;

import java.util.List;

/**
 * @description: GPS规则配置Service
 * @Date : 下午5:47 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public interface GpsRuleService extends BaseService<GpsRule,Integer,GpsRuleForm> {

    /**
     * 查询未绑定启用产品的gps规则
     * @return
     */
    List<GpsRule> queryRulesUnBindProduct();
}
