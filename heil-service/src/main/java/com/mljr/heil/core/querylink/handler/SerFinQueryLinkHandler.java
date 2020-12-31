package com.mljr.heil.core.querylink.handler;

import com.lyqc.base.page.PageForm;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.heil.base.vo.BaseVo;
import com.mljr.heil.core.querylink.QueryLinkContext;
import com.mljr.heil.entity.SerFinRule;
import com.mljr.heil.facade.rule.SerFinRuleFacade;
import com.mljr.heil.voconvertor.rule.SerFinRuleVoConvertor;
import com.mljr.util.CollectionsTools;

import java.util.List;

/**
 * @description: 平台费规则查询产品关联处理器
 * @Date : 2018/11/20 下午5:47
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class SerFinQueryLinkHandler extends AbstractQueryLinkHandler {
    protected SerFinRuleFacade serFinRuleFacade;
    /**
     * 构造函数
     */
    public SerFinQueryLinkHandler() {
        super("平台费规则", TagConstant.BuzTypeEnum.SER_FIN_RULE);
    }

    @Override
    protected void prepare(QueryLinkContext context) {
        super.prepare(context);
        serFinRuleFacade = context.getSerFinRuleFacade();
    }

    @Override
    public List<SerFinRule> queryLink(PageForm form) {
        return pdRuleProductService.querySerFinRules(form);
    }

    @Override
    public int queryLinkCount(PageForm form) {
        return pdRuleProductService.querySerFinRulesCount(form);
    }

    @Override
    List<BaseVo> convertVo() {
        return new SerFinRuleVoConvertor().convertList(entities);
    }

    @Override
    protected void call(QueryLinkContext context) {
        super.call(context);
        if(CollectionsTools.isNotEmpty(voList)){
            serFinRuleFacade.formatSerFinRule(entities, voList);
        }
    }
}
