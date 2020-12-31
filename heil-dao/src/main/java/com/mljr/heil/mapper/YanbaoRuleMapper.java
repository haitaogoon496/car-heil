package com.mljr.heil.mapper;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.YanbaoRule;
import com.mljr.heil.form.DealerRuleSetForm;
import com.mljr.heil.form.YanbaoRuleForm;

import java.util.List;

/**
 * @description: 第二/三年保险费规则配置
 * @Date : 2018/2/11 下午3:29
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface YanbaoRuleMapper extends BaseMapper<YanbaoRule,Integer,YanbaoRuleForm> {

    /**
     * 查询绑定/未绑定当前门店的第二/三年保险费规则
     * @param pageForm
     * @return
     */
    List<YanbaoRule> queryYanbaoRulesForDealer(PageForm<DealerRuleSetForm> pageForm);

    int queryYanbaoRulesForDealerCount(PageForm<DealerRuleSetForm> pageForm);
}