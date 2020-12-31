package com.mljr.heil.core.querylink.handler;

import com.lyqc.base.page.PageForm;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.heil.base.vo.BaseVo;
import com.mljr.heil.core.querylink.QueryLinkContext;
import com.mljr.heil.entity.GpsRule;
import com.mljr.heil.facade.rule.GpsRuleFacade;
import com.mljr.heil.voconvertor.rule.GpsRuleVoConvertor;
import com.mljr.util.CollectionsTools;

import java.util.List;

/**
 * @description: GPS规则查询产品关联处理器
 * @Date : 2018/11/20 下午5:47
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class GpsQueryLinkHandler extends AbstractQueryLinkHandler {
    protected GpsRuleFacade gpsRuleFacade;
    /**
     * 构造函数
     */
    public GpsQueryLinkHandler() {
        super("GPS规则", TagConstant.BuzTypeEnum.GPS_RULE);
    }

    @Override
    protected void prepare(QueryLinkContext context) {
        super.prepare(context);
        gpsRuleFacade = context.getGpsRuleFacade();
    }

    @Override
    public List<GpsRule> queryLink(PageForm form) {
        return pdRuleProductService.queryGpsRules(form);
    }

    @Override
    public int queryLinkCount(PageForm form) {
        return pdRuleProductService.queryGpsRulesCount(form);
    }

    @Override
    List<BaseVo> convertVo() {
        return new GpsRuleVoConvertor().convertList(entities);
    }

    @Override
    protected void call(QueryLinkContext context) {
        super.call(context);
        if(CollectionsTools.isNotEmpty(voList)){
            gpsRuleFacade.formatGpsRuleVo(voList);
        }
    }
}
