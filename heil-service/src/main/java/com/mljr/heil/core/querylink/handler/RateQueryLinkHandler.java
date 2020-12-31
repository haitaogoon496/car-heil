package com.mljr.heil.core.querylink.handler;

import com.lyqc.base.page.PageForm;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.heil.base.vo.BaseVo;
import com.mljr.heil.entity.RateRule;
import com.mljr.heil.voconvertor.rule.RateRuleVoConvertor;

import java.util.List;

/**
 * @description: 利率规则查询产品关联处理器
 * @Date : 2018/11/20 下午5:47
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class RateQueryLinkHandler extends AbstractQueryLinkHandler {
    /**
     * 构造函数
     */
    public RateQueryLinkHandler() {
        super("利率规则", TagConstant.BuzTypeEnum.RATE_RULE);
    }

    @Override
    public List<RateRule> queryLink(PageForm form) {
        return pdRuleProductService.queryRateRules(form);
    }

    @Override
    public int queryLinkCount(PageForm form) {
        return pdRuleProductService.queryRateRulesCount(form);
    }

    @Override
    List<BaseVo> convertVo() {
        return new RateRuleVoConvertor().convertList(entities);
    }
}
