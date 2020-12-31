package com.mljr.heil.service;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.entity.ProductProps;
import com.mljr.heil.form.ProductPropsForm;
import com.mljr.heil.service.product.ProductPropsService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Date : 2018/3/15$ 15:44$
 * @Author : liht
 */
public class ProductPropsServiceTest extends AbstractTest {

    @Resource
    ProductPropsService productPropsService;

    static ProductProps productProps;

    @BeforeClass
    public static void before() {
        productProps = new ProductProps();
        productProps.setpId(1024);
        productProps.setPropName("liht");
        productProps.setPropValue("lihtval");
        productProps.setDesp("liht junit测试");

    }

    @Test
    public void testInsert() {
        int id = productPropsService.insertRecord(productProps);
        Assert.assertTrue(id > 0 );
    }

    @Test
    public void testDel() {
        long id = productPropsService.insertRecord(productProps);
        int count = productPropsService.deleteRecord(id);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testUpdate() {
        long id = productPropsService.insertRecord(productProps);
        productProps = productPropsService.queryRecord(id);
        productProps.setPropValue("liht 修改了");
        productProps = productPropsService.queryRecord(id);
        Assert.assertEquals("liht 修改了", productProps.getPropValue());

    }

    @Test
    public void testPageQuery() {
        ProductPropsForm form = new ProductPropsForm();
        form.setProductId(26);
        List<ProductProps> list = productPropsService.queryByPage(new PageForm<>(form));

        Assert.assertTrue(list.size() > 0);

    }

    @Test
    public void testQueryRecoudByParam() {

        productPropsService.insertRecord(productProps);
        productProps.setId(null);
        productProps.setPropName("liht1");
        productProps.setPropValue("lihtval1");

        productPropsService.insertRecord(productProps);
        ProductPropsForm form = new ProductPropsForm();
        form.setProductId(1024);

        int count = productPropsService.delByForm(form);
        Assert.assertTrue(count == 2);
    }
}
