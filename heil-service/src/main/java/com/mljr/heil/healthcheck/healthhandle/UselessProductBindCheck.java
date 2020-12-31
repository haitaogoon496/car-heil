package com.mljr.heil.healthcheck.healthhandle;

import com.lyqc.base.page.PageForm;
import com.lyqc.transfer.dto.DealerDTO;
import com.lyqc.transfer.re.DealerRe;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.common.enums.HealthCheckIndexEnum;
import com.mljr.heil.component.DealerComponent;
import com.mljr.heil.entity.Product;
import com.mljr.heil.form.ProductForm;
import com.mljr.heil.service.product.ProductDealerService;
import com.mljr.heil.service.product.ProductService;
import com.mljr.heil.vo.healthCheck.HealthCheckMainPartVo;
import com.mljr.util.CollectionsTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:   失效产品检查
 * @Date : 2018/10/18 18:27
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Service
public class UselessProductBindCheck extends AbstractProductBindCheck{

    @Autowired
    ProductService productService;

    @Autowired
    private DealerComponent dealerComponent;

    @Autowired
    private ProductDealerService productDealerService;

    @Override
    public void doCheck() {
        List<HealthCheckMainPartVo> healthCheckMainPartVos = new ArrayList<>();
        // 查询所有有效门店
        List<DealerRe> dealerRelist = dealerComponent.queryList(PageForm.newInstance(Boolean.FALSE, DealerDTO.builder().status("1").build()));
        List<Integer> validDealerCodelist = dealerRelist.stream().map(DealerRe::getDealerCode).collect(Collectors.toList());
        // 查询不适用于所有门店的失效（status = 5）产品列表
        List<Product> unValidProductList = productService.queryList(ProductForm.builder().status(5).isAllDealer("0").build());
        // 查询产品门店关系，过滤出门店有效的关系
        List<BaseDealerRes> bindDealerProductList = productDealerService.queryList(null);
        Map<Integer, List<BaseDealerRes>> productDealerRefList = bindDealerProductList.stream().filter(baseDealer -> validDealerCodelist.contains(baseDealer.getDealerCode())).collect(Collectors.groupingBy(BaseDealerRes::getpId));
        // 过滤配置门店的失效产品列表
        List<Product> bindDealerList = unValidProductList.stream().filter(product -> CollectionsTools.isNotEmpty(productDealerRefList.get(product.getpId()))).collect(Collectors.toList());
        if(CollectionsTools.isNotEmpty(bindDealerList)) {
            bindDealerList.forEach(p -> healthCheckMainPartVos.add(HealthCheckMainPartVo.builder().event(p.getpId()).eventName(p.getpName()).eventDetail(p.getDesp()).build()));
        }
        healthCheckMap.put(HealthCheckIndexEnum.USELESSPRODUCT_BINDCHECK,healthCheckMainPartVos);
    }
}
