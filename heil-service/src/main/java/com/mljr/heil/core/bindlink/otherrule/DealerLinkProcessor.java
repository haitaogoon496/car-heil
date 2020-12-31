package com.mljr.heil.core.bindlink.otherrule;

import com.alibaba.fastjson.JSONObject;
import com.lyqc.base.page.PageForm;
import com.lyqc.transfer.dto.DealerDTO;
import com.lyqc.transfer.re.DealerRe;
import com.mljr.heil.component.DealerComponent;
import com.mljr.heil.core.bindlink.AbstractLinkProcessor;
import com.mljr.heil.core.bindlink.RuleServiceEnum;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 运营门店关联处理器
 * @Date : 2018/10/19 下午6:04
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class DealerLinkProcessor extends AbstractLinkProcessor {
    /**
     * 构造函数
     */
    public DealerLinkProcessor() {
        super("运营门店");
    }

    @Override
    protected void init() {
        sourceList = batchLinkParam.getDealerCodes();
        ruleDealerService = context.getServiceMap().get(RuleServiceEnum.PRODUCT_DEALER);
    }

    @Override
    protected void emptyInputItem() {
        feeRuleVo.setDealerNotExist(sourceList);
    }

    @Override
    public List<Integer> dataList() {
        DealerComponent dealerComponent = context.getDealerComponent();
        List<DealerRe> dealerReList = dealerComponent.queryList(PageForm.newInstance(Boolean.FALSE, DealerDTO.builder().dealerScopes(sourceList).build()));
        return dealerReList.stream().map(dealer -> dealer.getDealerCode()).collect(Collectors.toList());
    }

    @Override
    public void accept(List<Integer> integers, JSONObject jsonObject) {
        feeRuleVo.setDealerNotExist(integers);
        jsonObject.put("buzType",buzType);
    }

    @Override
    public boolean skip() {
        return batchLinkParam.isBindDealer();
    }

    @Override
    public void bindProduct(List<Integer> productIds, List<Integer> linkList) {
        super.bindDealer(linkList, productIds);
    }
}
