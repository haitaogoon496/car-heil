package com.mljr.heil.core.bindlink.otherrule;

import com.alibaba.fastjson.JSONObject;
import com.mljr.heil.core.bindlink.AbstractLinkProcessor;
import com.mljr.heil.core.bindlink.RuleServiceEnum;
import com.mljr.heil.form.ProductForm;
import com.mljr.heil.service.product.ProductService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 车贷产品关联处理器
 * @Date : 2018/10/19 下午6:04
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class ProductLinkProcessor extends AbstractLinkProcessor {
    /**
     * 构造函数
     */
    public ProductLinkProcessor() {
        super("车贷产品");
    }

    @Override
    protected void init() {
        sourceList = batchLinkParam.getpIds();
        ruleService = context.getServiceMap().get(RuleServiceEnum.PRODUCT);
        ruleDealerService = context.getServiceMap().get(RuleServiceEnum.PRODUCT_DEALER);
    }

    @Override
    protected void emptyInputItem() {
        feeRuleVo.setPIdNotExist(sourceList);
    }

    @Override
    public List<Integer> dataList() {
        ruleForm = new ProductForm();
        ruleForm.setRuleIds(batchLinkParam.getpIds());
        List<Integer> des = ((ProductService)ruleService).queryList((ProductForm) ruleForm).stream().map(rule -> rule.getpId()).collect(Collectors.toList());
        return des;
    }

    @Override
    public void accept(List<Integer> integers, JSONObject jsonObject) {
        feeRuleVo.setPIdNotExist(integers);
        jsonObject.put("buzType",buzType);
    }

    @Override
    public boolean skip() {
        return batchLinkParam.isBindProduct();
    }
}
