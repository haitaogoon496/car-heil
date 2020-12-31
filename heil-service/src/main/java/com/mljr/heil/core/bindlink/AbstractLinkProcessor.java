package com.mljr.heil.core.bindlink;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.BaseRuleForm;
import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.entity.PdRuleProduct;
import com.mljr.heil.param.config.BatchLinkParam;
import com.mljr.heil.vo.config.DealerFeeRuleVo;
import com.mljr.util.CollectionsTools;
import com.mljr.util.TimeTools;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @description: 抽象数据关联处理接口
 * @Date : 2018/10/17 下午6:45
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Slf4j
public abstract class AbstractLinkProcessor implements LinkProcess,BiConsumer<List<Integer>, JSONObject>{
    protected final String LOG_TITLE = "批量关联设置";
    /**
     * 标示费用类型
     * {@link TagConstant.BuzTypeEnum#getIndex()}
     */
    protected Integer classify;
    /**
     * 处理器名称
     */
    protected String processorName;
    /**
     *
     */
    protected BaseService ruleService;
    /**
     *
     */
    protected BaseService ruleDealerService;
    /**
     * 前端输入数据（规则ID或者产品ID）
     */
    protected List<Integer> sourceList;
    /**
     * 真正要处理的合法运营门店
     */
    protected List<Integer> dealerProcesses;
    /**
     * 真正要处理的合法产品ID
     */
    protected List<Integer> productProcesses;
    /**
     * 上下文对象
     */
    protected LinkProcessContext context;
    /**
     * 封装请求参数
     */
    protected BatchLinkParam batchLinkParam;
    /**
     * 规则查询Form对象
     */
    protected BaseRuleForm ruleForm;
    /**
     * 业务类型
     */
    protected LinkProcessBuzType buzType;
    /**
     *
     */
    protected DealerFeeRuleVo feeRuleVo;

    /**
     * 构造函数
     * @param processorName
     */
    public AbstractLinkProcessor(String processorName) {
        this.processorName = processorName;
    }

    /**
     * 子类初始化
     */
    protected abstract void init();
    /**
     * 空项处理
     */
    protected abstract void emptyInputItem();
    /**
     * 初始化
     */
    protected void prepare(LinkProcessContext ctx){
        context = ctx;
        if(null == context.getOperateLog()){
            context.setOperateLog(new JSONObject(true));
        }
        feeRuleVo = context.getFeeRuleVo();
        buzType = context.getBuzType();
        batchLinkParam = context.getBatchLinkParam();
        dealerProcesses = context.getDealerScope();
        productProcesses = context.getProductScope();
    }

    /**
     * 对外部调用统一入口
     */
    public final void execute(LinkProcessContext ctx){
        prepare(ctx);
        init();
        if(skip()){
            log.info("skip this process【{}】,buzType={}",processorName,buzType);
            return;
        }
        if(null != sourceList && !sourceList.isEmpty()){
            handleData(processList -> {
                if(batchLinkParam.isBindDealer()){
                    bindDealer(dealerProcesses,processList);
                }
                if(batchLinkParam.isBindProduct()){
                    bindProduct(productProcesses,processList);
                }
            },(noExits,json) -> accept(noExits,json));
        }else{
            emptyInputItem();
        }
        ctx.getOperateLog().putAll(context.getOperateLog());
    }

    @Override
    public void handleData( Consumer<List<Integer>> c1, BiConsumer<List<Integer>, JSONObject> c2) {
        //不合法的规则ID
        Set<Integer> difference = Sets.difference(Sets.newHashSet(sourceList),Sets.newHashSet(dataList()));
        //真正要处理的规则ID
        Set<Integer> intersection = Sets.intersection(Sets.newHashSet(sourceList),Sets.newHashSet(dataList()));
        List<Integer> processList = Lists.newArrayList(intersection);
        c1.accept(processList);
        c2.accept(Lists.newArrayList(difference),context.getOperateLog());
        if(batchLinkParam.isBindDealer()){
            context.getOperateLog().put("dealerCodes",Optional.ofNullable(dealerProcesses).orElse(Collections.emptyList()).toString());
        }
        if(batchLinkParam.isBindProduct()){
            context.getOperateLog().put("productIds", Optional.ofNullable(productProcesses).orElse(Collections.emptyList()).toString());
        }
        context.getOperateLog().put(processorName,processList.toString());
    }

    @Override
    public void bindDealer(List<Integer> dealerCodes, List<Integer> linkList) {
        if(CollectionsTools.isEmpty(dealerCodes) || CollectionsTools.isEmpty(linkList)){
            return;
        }
        log.info("{}，【{}】,linkList={},dealerCodes={},buzType={}", LOG_TITLE,processorName, linkList.toString(), dealerCodes.toString(), buzType);
        dealerCodes.forEach(dealerCode -> {
            List<BaseDealerRes> ruleDealers = new ArrayList<>(linkList.size());
            Date now = TimeTools.createNowTime();
            BaseDealerRes ruleDealer;
            for(Integer ruleId : linkList){
                ruleDealer = new BaseDealerRes();
                ruleDealer.setRuleSeq(ruleId);
                ruleDealer.setDealerCode(dealerCode);
                ruleDealer.setUpdateTime(now);
                ruleDealers.add(ruleDealer);
            }
            if(buzType == LinkProcessBuzType.INSERT){
                ruleDealerService.batchInsertIgnore(ruleDealers);
            }
            if(buzType == LinkProcessBuzType.DELETE){
                ruleDealerService.batchDelete(ruleDealers);
            }
        });
    }

    @Override
    public void bindProduct(List<Integer> productIds, List<Integer> linkList) {
        if(CollectionsTools.isEmpty(productIds) || CollectionsTools.isEmpty(linkList)){
            return;
        }
        log.info("{},【{}】,linkList={},productIds={},buzType={}", LOG_TITLE,processorName, linkList.toString(), productProcesses.toString(), buzType);
        productIds.forEach(productId -> {
            List<PdRuleProduct> batchList = new ArrayList<>(linkList.size());
            linkList.forEach(eachId -> batchList.add(PdRuleProduct.builder().classify(classify.byteValue()).productId(productId).ruleId(eachId).build()));
            if(buzType == LinkProcessBuzType.INSERT){
                context.getServiceMap().get(RuleServiceEnum.RULE_PRODUCT).batchInsertIgnore(batchList);
            }
            if(buzType == LinkProcessBuzType.DELETE){
                context.getServiceMap().get(RuleServiceEnum.RULE_PRODUCT).batchDelete(batchList);
            }
        });
    }
}
