package com.mljr.heil.mapper;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.RuleRefForm;
import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.AccountRule;
import com.mljr.heil.entity.GpsRule;
import com.mljr.heil.entity.PdRuleProduct;
import com.mljr.heil.entity.RateRule;
import com.mljr.heil.entity.SerFinRule;
import com.mljr.heil.entity.YanbaoRule;
import com.mljr.heil.form.PdRuleProductForm;

import java.util.List;

public interface PdRuleProductMapper extends BaseMapper<PdRuleProduct,Integer,PdRuleProductForm>{

    /**
     * 平台费查询关联产品
     * @param pageForm
     * @return
     */
    List<SerFinRule> querySerFinRules(PageForm<PdRuleProductForm> pageForm);
    int querySerFinRulesCount(PageForm<PdRuleProductForm> pageForm);

    /**
     * 查询绑定产品的利率规则
     * @param pageForm
     * @return
     */
    List<RateRule> queryRateRules(PageForm<PdRuleProductForm> pageForm);

    int queryRateRulesCount(PageForm<PdRuleProductForm> pageForm);

    /**
     * 查询绑定产品的GPS规则
     * @param pageForm
     * @return
     */
    List<GpsRule> queryGpsRules(PageForm<PdRuleProductForm> pageForm);

    int queryGpsRulesCount(PageForm<PdRuleProductForm> pageForm);

    /**
     * 查询绑定产品的第二年/第三年保险规则
     * @param pageForm
     * @return
     */
    List<YanbaoRule> queryYanbaoRules(PageForm<PdRuleProductForm> pageForm);

    int queryYanbaoRulesCount(PageForm<PdRuleProductForm> pageForm);

    /**
     * 查询绑定产品的账户管理费规则
     * @param pageForm
     * @return
     */
    List<AccountRule> queryAccountRules(PageForm<PdRuleProductForm> pageForm);

    int queryAccountRulesCount(PageForm<PdRuleProductForm> pageForm);

    /**
     * 查询规则产品关系列表，使用 ${}，参数数据来源 {@link com.mljr.heil.common.enums.RuleBindProductCheckTableEnum}
     * @param form
     * @return
     */
    List<BaseDealerRes> queryRuleProductListByParam(RuleRefForm form);

}