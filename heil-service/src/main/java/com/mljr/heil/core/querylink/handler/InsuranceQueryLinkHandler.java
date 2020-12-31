package com.mljr.heil.core.querylink.handler;

import com.lyqc.base.page.PageForm;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.heil.base.vo.BaseVo;
import com.mljr.heil.core.querylink.QueryLinkContext;
import com.mljr.heil.entity.YanbaoRule;
import com.mljr.heil.facade.rule.YanbaoRuleFacade;
import com.mljr.heil.voconvertor.rule.YanbaoRuleVoConvertor;
import com.mljr.util.CollectionsTools;

import java.util.List;

/**
 * @description: 保险费规则查询产品关联处理器
 * @Date : 2018/11/20 下午5:47
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class InsuranceQueryLinkHandler extends AbstractQueryLinkHandler {
    /**
     * 延保类型
     */
    protected Integer yanBaoClassify;
    protected YanbaoRuleFacade yanbaoRuleFacade;

    public InsuranceQueryLinkHandler(Integer yanBaoClassify) {
        super(String.format("第%s年保险费规则",yanBaoClassify), TagConstant.BuzTypeEnum.YAN_BAO_RULE);
        this.yanBaoClassify = yanBaoClassify;
    }

    @Override
    protected void prepare(QueryLinkContext context) {
        context.getForm().getForm().setYanBaoClassify(yanBaoClassify);
        super.prepare(context);
        yanbaoRuleFacade = context.getYanbaoRuleFacade();
    }

    @Override
    protected void call(QueryLinkContext context) {
        super.call(context);
        if(CollectionsTools.isNotEmpty(voList)){
            yanbaoRuleFacade.formatYanbaoRules(entities, voList);
        }
    }

    @Override
    public List<YanbaoRule> queryLink(PageForm form) {
        return pdRuleProductService.queryYanbaoRules(form);
    }

    @Override
    public int queryLinkCount(PageForm form) {
        return pdRuleProductService.queryYanbaoRulesCount(form);
    }

    @Override
    List<BaseVo> convertVo() {
        return new YanbaoRuleVoConvertor().convertList(entities);
    }
}
