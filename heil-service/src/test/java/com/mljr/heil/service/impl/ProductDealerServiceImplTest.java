package com.mljr.heil.service.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.entity.ProductDealer;
import com.mljr.heil.service.product.ProductDealerService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @description:
 * @Date : 2018/3/16$ 16:30$
 * @Author : liht
 */
public class ProductDealerServiceImplTest extends AbstractTest{

    @Resource
    ProductDealerService productDealerService;
    static ProductDealer productDealer;

    @BeforeClass
    public static void before() {
        productDealer = new ProductDealer();
        productDealer.setRuleSeq(121);
        productDealer.setpId(121);
        productDealer.setDealerCode(2342343);

    }

    @Test
    public void queryByPage() throws Exception {
        DealerQueryForm form = new DealerQueryForm();
        form.setId(100);

        List<BaseDealerRes> list = productDealerService.queryByPage(new PageForm<>(form));
        assertTrue(list.size()>0);
    }


    @Test
    public void insertRecord() throws Exception {
        int i = productDealerService.insertRecord(productDealer);
        assertTrue(i == 1);
    }



    @Test
    public void deleteRecord() throws Exception {
        productDealerService.insertRecord(productDealer);
        int i = productDealerService.deleteRecord(productDealer);
        assertTrue(i == 1);
    }

}