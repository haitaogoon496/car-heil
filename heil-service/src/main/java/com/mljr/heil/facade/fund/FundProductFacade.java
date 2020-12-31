package com.mljr.heil.facade.fund;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.annotation.LogMonitor;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.entity.Product;
import com.mljr.heil.form.fund.FundProductForm;
import com.mljr.heil.service.fund.FundProductService;
import com.mljr.heil.vo.product.ProductVo;
import com.mljr.heil.voconvertor.product.ProductVoConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @description: 资金方--产品 绑定关系 facade
 * @Date : 2018/6/13 14:48
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Component
public class FundProductFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.FUND.getName();

    @Autowired
    FundProductService fundProductService;

    /**
     * 分页查出当前资金方绑定产品列表
     * @param form
     * @return
     */
    @LogMonitor("资金方管理")
    public Result<PageVO<ProductVo>> loadBindRecords(PageForm<FundProductForm> form) {
        List<ProductVo> productVos = Collections.emptyList();
        int count = 0;
        try {
            List<Product> products = fundProductService.selectBindProductByParams(form);
            if (CollectionUtils.isEmpty(products)) {
                return Result.failInEmptyRecord(null);
            }
            productVos = new ProductVoConvertor().convertList(products);
            count = fundProductService.getBindProductCount(form);
        }catch (Exception e){
            LOGGER.error("{}loadBindRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return  Result.fail(new PageVO<>(form.getDraw(),count,productVos));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,productVos));
    }

    /**
     * 分页查出当前资金方尚未绑定产品列表
     * @param form
     * @return
     */
    @LogMonitor("资金方管理")
    public Result<PageVO<ProductVo>> loadNotBindRecords(PageForm<FundProductForm> form) {
        List<ProductVo> productVos = Collections.emptyList();
        int count = 0;
        try {
            List<Product> products = fundProductService.selectNotBindProductByParams(form);
            if (CollectionUtils.isEmpty(products)) {
                return Result.failInEmptyRecord(null);
            }
            productVos = new ProductVoConvertor().convertList(products);
            count = fundProductService.getNotBindProductCount(form);
        }catch (Exception e){
            LOGGER.error("{}loadNotBindRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return  Result.fail(new PageVO<>(form.getDraw(),count,productVos));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,productVos));
    }

}
