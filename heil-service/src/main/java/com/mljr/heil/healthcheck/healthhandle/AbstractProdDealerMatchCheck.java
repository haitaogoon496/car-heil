package com.mljr.heil.healthcheck.healthhandle;

import com.lyqc.base.page.PageForm;
import com.lyqc.transfer.dto.DealerDTO;
import com.lyqc.transfer.re.DealerRe;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.common.enums.HealthCheckIndexEnum;
import com.mljr.heil.common.enums.RuleBindDealerCheckTableEnum;
import com.mljr.heil.common.enums.RuleBindProductCheckTableEnum;
import com.mljr.heil.component.DealerComponent;
import com.mljr.heil.entity.Product;
import com.mljr.heil.form.ProductForm;
import com.mljr.heil.service.common.SyDealerService;
import com.mljr.heil.service.product.PdRuleProductService;
import com.mljr.heil.service.product.ProductDealerService;
import com.mljr.heil.service.product.ProductService;
import com.mljr.heil.vo.healthCheck.HealthCheckMainPartVo;
import com.mljr.util.CollectionsTools;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description:  抽象类，用于对比 平台费规则/利率规则/gps规则 门店和产品绑定有无冲突
 * @Date : 2018/9/20 11:50
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public abstract class AbstractProdDealerMatchCheck extends  AbstractHealthCheck{

    @Autowired
    ProductService productService;


    @Autowired
    ProductDealerService productDealerService;

    @Autowired
    private SyDealerService syDealerService;

    @Autowired
    private PdRuleProductService pdRuleProductService;

    @Autowired
    private DealerComponent dealerComponent;

    /**
     * 规则门店关系枚举
     */
    protected RuleBindDealerCheckTableEnum bindDealerTableEnum;

    /**
     * 规则产品关系枚举
     */
    protected RuleBindProductCheckTableEnum bindProductTableEnum;

    /**
     * 健康检查枚举
     */
    protected HealthCheckIndexEnum healthEnum;

    @PostConstruct
    public void init() {
        perpare();
    }

    /**
     * 前置处理
     */
    protected abstract void perpare();

    @Override
    public void doCheck() {
        List<HealthCheckMainPartVo> healthCheckMainPartVoList = new ArrayList<>();
        // 查询有效的门店code列表
        List<DealerRe> dealerRelist = dealerComponent.queryList(PageForm.newInstance(Boolean.FALSE, DealerDTO.builder().status("1").build()));
        List<Integer> validDealerCodelist = dealerRelist.stream().map(DealerRe::getDealerCode).collect(Collectors.toList());
        //查询每个门店，以及门店下的相应规则id集合
        Map<Integer, List<Integer>> dealerRules = dealerRules(validDealerCodelist);
        // 查询所有有效产品
        List<Product> productList = productService.queryList(ProductForm.builder().status(1).build());
        List<Integer> validProductIdList = productList.stream().map(Product::getpId).collect(Collectors.toList());
        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getpId, Function.identity()));
        // 查询产品规则关系
        List<BaseDealerRes> bindRuleProductList = pdRuleProductService.queryRuleProductListByParam(bindProductTableEnum);
        Map<Integer, List<BaseDealerRes>> productRuleRefs = bindRuleProductList.stream().collect(Collectors.groupingBy(BaseDealerRes::getpId));
        // 查询产品门店关系，并过滤出有效的绑定关系（产品有效，门店有效）
        List<BaseDealerRes> bindDealerProductList = productDealerService.queryList(null);
        Map<Integer, List<BaseDealerRes>> productDealerRefs = bindDealerProductList.stream().filter(d -> validProductIdList.contains(d.getpId()) && validDealerCodelist.contains(d.getDealerCode())).collect(Collectors.groupingBy(BaseDealerRes::getpId));
        // 遍历对，所有有效产品，进行处理，对比与绑定门店配置规则是否有重叠，没有则元数据异常
        productDealerRefs.forEach((pId, baseDealerRes) -> {
            // 异常门店集合
            List<Integer> unMatchDealerCode = new ArrayList<>();
            // 该产品的规则Id&门店code列表
            List<BaseDealerRes> productRuleRefList = productRuleRefs.get(pId);
            List<Integer> refDealerList = baseDealerRes.stream().map(BaseDealerRes::getDealerCode).collect(Collectors.toList());
            // 如果产品没有绑定规则，则所有门店配置有误（门店可选产品，却匹配不到共有规则）
            if(CollectionsTools.isNotEmpty(productRuleRefList)) {
                // 产品绑定规则列表
                List<Integer> refRuleList = productRuleRefList.stream().map(BaseDealerRes::getRuleSeq).collect(Collectors.toList());
                // 遍历产品关联的门店列表，查看是否有不包含共有规则的门店
                refDealerList.forEach(dealerCode -> {
                    // 门店绑定规则列表
                    List<Integer> dealerRuleRefList = dealerRules.get(dealerCode);
                    // 匹配共有规则，匹配不到配置有误
                    if(CollectionsTools.isNotEmpty(dealerRuleRefList)) {
                        boolean contains = false;
                        for(Integer ruleId : dealerRuleRefList) {
                            if(refRuleList.contains(ruleId)) {
                                contains = true;
                                break;
                            }
                        }
                        if(!contains) {
                            unMatchDealerCode.add(dealerCode);
                        }
                    } else {
                        unMatchDealerCode.add(dealerCode);
                    }
                });
            } else {
                unMatchDealerCode.addAll(refDealerList);
            }
            //将此产品绑定平台费而不匹配门店的列出来（按理来说产品和门店同时绑定同一个平台费规则，那么才能使用）
            if (unMatchDealerCode.size() > 0) {
                HealthCheckMainPartVo healthCheckMainPartVo = new HealthCheckMainPartVo();
                healthCheckMainPartVo.setEvent(pId);
                healthCheckMainPartVo.setEventName(productMap.get(pId).getpName());
                healthCheckMainPartVo.setEventDetail(unMatchDealerCode.stream().map(id -> id.toString()).collect(Collectors.joining(",")));
                healthCheckMainPartVoList.add(healthCheckMainPartVo);
            }
        });
        healthCheckMap.put(healthEnum, healthCheckMainPartVoList);
    }

    /**
     *  查询每个门店，以及门店下的相应规则id集合
     */
    private Map<Integer, List<Integer>> dealerRules(List<Integer> validDealerCodelist){
        //查询每个门店，以及门店下的相应规则id集合
        Map<Integer, List<Integer>> dealerRules = new HashMap<>();
        // 查询规则关联关系
        List<BaseDealerRes> dealerResList = syDealerService.queryRuleDealerListByParam(bindDealerTableEnum);
        Map<Integer, List<BaseDealerRes>> bindDealerGroup = dealerResList.stream().filter(dealerRes -> dealerRes.getDealerCode() != null).collect(Collectors.groupingBy(BaseDealerRes::getDealerCode));
        // 查询适用于所有门店的规则
        List<Integer> isAllDealerRuleIdList = syDealerService.queryIsAllDealerRuleIdList(bindDealerTableEnum);
        // 组织数据
        validDealerCodelist.forEach(dealerCode -> {
            List<Integer> tempList = null;
            List<BaseDealerRes> baseDealerRes = bindDealerGroup.get(dealerCode);
            if(CollectionsTools.isNotEmpty(baseDealerRes)) {
                tempList = bindDealerGroup.get(dealerCode).stream().map(BaseDealerRes::getRuleSeq).collect(Collectors.toList());
                tempList.addAll(isAllDealerRuleIdList);
            } else {
                tempList = isAllDealerRuleIdList;
            }
            dealerRules.put(dealerCode, tempList);
        });
        return dealerRules;
    }

}
