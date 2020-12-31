package com.mljr.heil.facade;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.entity.fund.FundProduct;
import com.mljr.heil.facade.fund.FundProductFacade;
import com.mljr.heil.form.fund.FundProductForm;
import com.mljr.heil.vo.product.ProductVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @Date : 2018/6/13 17:05
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public class FundProductFacadeTest extends AbstractTest{

    @Autowired
    FundProductFacade fundProductFacade;

    @Test
    public void loadBindRecordsTest(){
        PageForm<FundProductForm> form = new PageForm<>();
        form.setLimit(5);
        FundProductForm productForm = new FundProductForm();
        productForm.setFundId(4);
        productForm.setProductName("浦");
        productForm.setProductId(30);
        form.setForm(productForm);
        Result<PageVO<ProductVo>>  re = fundProductFacade.loadBindRecords(form);
        System.out.println(re);
    }

    @Test
    public void loadNotBindRecordsTest(){
        PageForm<FundProductForm> form = new PageForm<>();
        form.setLimit(5);
        FundProductForm productForm = new FundProductForm();
        productForm.setFundId(4);
        productForm.setProductName("浦");
        form.setForm(productForm);
        Result<PageVO<ProductVo>>  re = fundProductFacade.loadNotBindRecords(form);
        System.out.println(re);
    }

}
