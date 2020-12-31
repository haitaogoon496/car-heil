package com.mljr.heil.core.querylink.handler;

import com.lyqc.base.page.PageForm;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.heil.base.vo.BaseVo;
import com.mljr.heil.entity.AccountRule;
import com.mljr.heil.voconvertor.rule.AccountRuleVoConvertor;

import java.util.List;

/**
 * @description: 账号管理费规则查询产品关联处理器
 * @Date : 2018/11/20 下午5:47
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class AccountQueryLinkHandler extends AbstractQueryLinkHandler {

    /**
     * 构造函数
     */
    public AccountQueryLinkHandler() {
        super("账号管理费规则", TagConstant.BuzTypeEnum.ACCOUNT_RULE);
    }

    @Override
    public List<AccountRule> queryLink(PageForm form) {
        return pdRuleProductService.queryAccountRules(form);
    }

    @Override
    public int queryLinkCount(PageForm form) {
        return pdRuleProductService.queryAccountRulesCount(form);
    }

    @Override
    List<BaseVo> convertVo() {
        return new AccountRuleVoConvertor().convertList(entities);
    }
}
