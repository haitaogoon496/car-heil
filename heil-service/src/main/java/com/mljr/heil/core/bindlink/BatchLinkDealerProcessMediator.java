package com.mljr.heil.core.bindlink;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lyqc.base.page.PageForm;
import com.lyqc.transfer.dto.DealerDTO;
import com.lyqc.transfer.re.DealerRe;
import com.mljr.heil.component.DealerComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 批量关联设置-门店编码关联-处理中介者对象
 * @Date : 2018/11/12 下午3:43
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Slf4j
public class BatchLinkDealerProcessMediator extends AbstractBatchLinkProcessMediator {
    public BatchLinkDealerProcessMediator() {
    }

    public BatchLinkDealerProcessMediator(LinkProcessContext context) {
        super(context);
    }

    @Override
    void handleBatchSources() {
        log.info("{}-门店关联-params:{}", LOG_TITLE,JSON.toJSON(context.getBatchLinkParam()));
        List<Integer> dealerScope = context.getBatchLinkParam().getDealerCodes();
        // 查询门店
        DealerComponent dealerComponent = context.getDealerComponent();
        List<DealerRe> dealerReList = dealerComponent.queryList(PageForm.newInstance(Boolean.FALSE, DealerDTO.builder().dealerScopes(dealerScope).build()));
        Assert.notEmpty(dealerReList, "批量门店编码集合为空");
        List<Integer> sources = dealerReList.stream().map(dealer -> dealer.getDealerCode()).collect(Collectors.toList());
        //非法数据
        Set<Integer> difference = Sets.difference(Sets.newHashSet(dealerScope),Sets.newHashSet(sources));
        //合法数据
        Set<Integer> intersection = Sets.intersection(Sets.newHashSet(dealerScope),Sets.newHashSet(sources));
        List<Integer> noExits = Lists.newArrayList(difference);
        context.getFeeRuleVo().setDealerNotExist(noExits);
        List<Integer> handleDealerScope = Lists.newArrayList(intersection);
        context.setDealerScope(handleDealerScope);
        log.info("{}-门店关联-合法数据:{},非法数据:{}", LOG_TITLE,JSON.toJSON(handleDealerScope), JSON.toJSON(noExits));
    }
}