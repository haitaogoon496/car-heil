package com.mljr.heil.core.bindlink;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mljr.heil.entity.Product;
import com.mljr.heil.form.ProductForm;
import com.mljr.heil.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 批量关联设置-产品ID关联-处理中介者对象
 * @Date : 2019/2/12 下午5:12
 * @Author : 石冬冬-Seig Heil
 */
@Slf4j
public class BatchLinkProductProcessMediator extends AbstractBatchLinkProcessMediator {
    public BatchLinkProductProcessMediator() {
    }

    public BatchLinkProductProcessMediator(LinkProcessContext context) {
        super(context);
    }

    @Override
    void handleBatchSources() {
        log.info("{}-产品关联-params:{}", LOG_TITLE,JSON.toJSON(context.getBatchLinkParam()));
        List<Integer> productScope = context.getBatchLinkParam().getpIds();
        // 查询门店
        ProductService productService = (ProductService)context.getServiceMap().get(RuleServiceEnum.PRODUCT);
        //经过数据库过滤查询真正要处理的产品对象集合
        List<Product> queryList = productService.queryList(ProductForm.builder().productIdList(productScope).build());
        Assert.notEmpty(queryList,"批量产品ID集合为空");
        List<Integer> sources = queryList.stream().map(each -> each.getpId()).collect(Collectors.toList());
        //非法数据
        Set<Integer> difference = Sets.difference(Sets.newHashSet(productScope),Sets.newHashSet(sources));
        //合法数据
        Set<Integer> intersection = Sets.intersection(Sets.newHashSet(productScope),Sets.newHashSet(sources));
        List<Integer> noExits = Lists.newArrayList(difference);
        context.getFeeRuleVo().setPIdNotExist(noExits);
        List<Integer> handleScope = Lists.newArrayList(intersection);
        context.setProductScope(handleScope);
        log.info("{}-产品关联-合法数据:{},非法数据:{}", LOG_TITLE,JSON.toJSON(handleScope), JSON.toJSON(noExits));
    }
}