package com.mljr.heil.mapper;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.GpsRule;
import com.mljr.heil.form.DealerRuleSetForm;
import com.mljr.heil.form.GpsRuleForm;

import java.util.List;

/**
 * @description: 车贷产品 Mapper
 * @Date : 2018/2/11 下午3:28
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface GpsRuleMapper extends BaseMapper<GpsRule,Integer,GpsRuleForm> {

    /**
     * 查询绑/未绑定定当前门店的gps规则
     * @param pageForm
     * @return
     */
    List<GpsRule> queryGpsRulesForDealer(PageForm<DealerRuleSetForm> pageForm);

    int queryGpsRulesForDealerCount(PageForm<DealerRuleSetForm> pageForm);

    /**
     * 查询未绑定启用产品的gps规则
     * @return
     */
    List<GpsRule> queryRulesUnBindProduct();
}