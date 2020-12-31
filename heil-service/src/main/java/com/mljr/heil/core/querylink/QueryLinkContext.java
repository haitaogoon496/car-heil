package com.mljr.heil.core.querylink;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.vo.BaseVo;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.facade.rule.GpsRuleFacade;
import com.mljr.heil.facade.rule.SerFinRuleFacade;
import com.mljr.heil.facade.rule.YanbaoRuleFacade;
import com.mljr.heil.form.PdRuleProductForm;
import com.mljr.heil.service.product.PdRuleProductService;
import com.mljr.redis.enums.BuzType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 查询费用规则关联上下文对象
 * @Date : 2018/11/20 下午2:16
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryLinkContext {
    protected PdRuleProductService pdRuleProductService;
    protected CommonComponent commonComponent;
    protected YanbaoRuleFacade yanbaoRuleFacade;
    protected SerFinRuleFacade serFinRuleFacade;
    protected GpsRuleFacade gpsRuleFacade;
    protected BuzType buzType;
    protected PageForm<PdRuleProductForm> form;
    protected int count;
    protected List<BaseVo> voList;
}