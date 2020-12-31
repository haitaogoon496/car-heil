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
 * @description:  （启用中的）产品是否有(平台费/gps/利率/门店)绑定
 * @Date : 2018/7/23 17:00
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Service
public class ProductBindRuleCheck extends AbstractProductBindCheck {

    @Autowired
    ProductService productService;

    @Autowired
    private DealerComponent dealerComponent;

    @Autowired
    private ProductDealerService productDealerService;

    @Override
    public void doCheck() {
        List<Product> productList = productService.queryProductNotSerFin();
        List<HealthCheckMainPartVo> healthCheckMainPartVos = new ArrayList<>();
        HealthCheckMainPartVo healthCheckMainPartVo = null;
        resultAddCheckMap(productList,healthCheckMainPartVos, p ->{
            p.setEventName("启用中产品未绑定必要平台费");
        });

        List<Product> productListForGps = productService.queryProductNotGpsOrRate("2");
        resultAddCheckMap(productListForGps,healthCheckMainPartVos, p ->{
            p.setEventName("启用中产品未绑定必要GPS");
        });

        List<Product> productListForRate = productService.queryProductNotGpsOrRate("3");
        resultAddCheckMap(productListForRate,healthCheckMainPartVos, p ->{
            p.setEventName("启用中产品未绑定必要利率规则");
        });

        // 查询所有有效门店
        List<DealerRe> dealerRelist = dealerComponent.queryList(PageForm.newInstance(Boolean.FALSE, DealerDTO.builder().status("1").build()));
        List<Integer> validDealerCodelist = dealerRelist.stream().map(DealerRe::getDealerCode).collect(Collectors.toList());
        // 查询不适用于所有门店的有效（status = 1）产品列表
        List<Product> validProductList = productService.queryList(ProductForm.builder().status(1).isAllDealer("0").build());
        // 查询产品门店关系，过滤出门店有效的关系
        List<BaseDealerRes> bindDealerProductList = productDealerService.queryList(null);
        Map<Integer, List<BaseDealerRes>> productDealerRefList = bindDealerProductList.stream().filter(baseDealer -> validDealerCodelist.contains(baseDealer.getDealerCode())).collect(Collectors.groupingBy(BaseDealerRes::getpId));
        // 过滤没有配置门店的有效产品
        List<Product> nilBindDealerList = validProductList.stream().filter(product -> CollectionsTools.isEmpty(productDealerRefList.get(product.getpId()))).collect(Collectors.toList());
        resultAddCheckMap(nilBindDealerList,healthCheckMainPartVos, p -> p.setEventName("启用中产品未绑定门店"));
        healthCheckMap.put(HealthCheckIndexEnum.PRODUCT_NO_NECESSARY_RULE,healthCheckMainPartVos);
    }
}
