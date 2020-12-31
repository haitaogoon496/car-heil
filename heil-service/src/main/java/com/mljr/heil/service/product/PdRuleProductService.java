package com.mljr.heil.service.product;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.common.enums.RuleBindProductCheckTableEnum;
import com.mljr.heil.entity.AccountRule;
import com.mljr.heil.entity.GpsRule;
import com.mljr.heil.entity.PdRuleProduct;
import com.mljr.heil.entity.RateRule;
import com.mljr.heil.entity.SerFinRule;
import com.mljr.heil.entity.YanbaoRule;
import com.mljr.heil.form.PdRuleProductForm;

import java.util.List;

/**
 * @description:费用规则和产品关联设置
 * @Date : 2018/8/6 14:47
 * @Author : lihaitao
 */
public interface PdRuleProductService extends BaseService<PdRuleProduct, Integer, PdRuleProductForm> {

	int deleteByForm(PdRuleProductForm form);

    /**
     * 平台费关联产品查询
     * @param pageForm
     * @return
     */
    List<SerFinRule> querySerFinRules(PageForm<PdRuleProductForm> pageForm);
    int querySerFinRulesCount(PageForm<PdRuleProductForm> pageForm);

    /**
     * 查询绑定当前产品的利率规则
     * @param pageForm
     * @return
     */
    List<RateRule> queryRateRules(PageForm<PdRuleProductForm> pageForm);

    /**
     * 查询未绑定当前产品的利率规则
     * @param pageForm
     * @return
     */
    int queryRateRulesCount(PageForm<PdRuleProductForm> pageForm);

    /**
     * 查询绑定当前产品的gps规则
     * @param pageForm
     * @return
     */
    List<GpsRule> queryGpsRules(PageForm<PdRuleProductForm> pageForm);

    /**
     * 查询未绑定当前产品的gps规则
     * @param pageForm
     * @return
     */
    int queryGpsRulesCount(PageForm<PdRuleProductForm> pageForm);

    /**
     * 查询绑定当前产品的第二年/三年保险规则
     * @param pageForm
     * @return
     */
    List<YanbaoRule> queryYanbaoRules(PageForm<PdRuleProductForm> pageForm);
    /**
     * 查询未绑定当前产品的第二年/三年保险规则
     * @param pageForm
     * @return
     */
    int queryYanbaoRulesCount(PageForm<PdRuleProductForm> pageForm);

    /**
     * 查询绑定当前产品的账户管理费规则
     * @param pageForm
     * @return
     */
    List<AccountRule> queryAccountRules(PageForm<PdRuleProductForm> pageForm);
    /**
     * 查询未绑定当前产品的账户管理费规则
     * @param pageForm
     * @return
     */
    int queryAccountRulesCount(PageForm<PdRuleProductForm> pageForm);

    /**
     * 查询规则产品关系列表
     * @param form
     * @return
     */
    List<BaseDealerRes> queryRuleProductListByParam(RuleBindProductCheckTableEnum form);

}
