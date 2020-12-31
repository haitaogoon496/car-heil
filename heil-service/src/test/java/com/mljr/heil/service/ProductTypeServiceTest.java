package com.mljr.heil.service;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.entity.ProductType;
import com.mljr.heil.form.ProductTypeForm;
import com.mljr.heil.service.product.ProductTypeService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Date : 2018/3/15$ 17:35$
 * @Author : liht
 */
public class ProductTypeServiceTest extends AbstractTest {

    @Resource
    ProductTypeService productTypeService;

    static ProductType productType;

    @BeforeClass
    public static void before() {
        productType = new ProductType();
        productType.setpClassCode("W");
        productType.setpClassName("lihtjunit測試");
        productType.setStatus("1");
        productType.setpDesc("liht測試");
        productType.setpTypeId(2000);
    }

    @Test
    public void queryByPage() throws Exception {

        ProductTypeForm form = new ProductTypeForm();
        form.setName("微");
        List<ProductType> list = productTypeService.queryByPage(new PageForm<>(form));
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void queryCount() throws Exception {
        ProductTypeForm form = new ProductTypeForm();
        form.setStatus("1");
        List<ProductType> list = productTypeService.queryByPage(new PageForm<>(form));
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void queryRecord() throws Exception {
        int id = productTypeService.insertRecord(productType);
        productType = productTypeService.queryRecord(id);
        Assert.assertTrue(productType != null);
    }

    @Test
    public void insertRecord() throws Exception {
        int id = productTypeService.insertRecord(productType);
        productType = productTypeService.queryRecord(id);
        Assert.assertTrue(productType != null);
    }

    @Test
    public void updateRecord() throws Exception {
        int id = productTypeService.insertRecord(productType);
        productType = productTypeService.queryRecord(id);
        productType.setpDesc("lihtjunit修改");
        productTypeService.updateRecord(productType);
        Assert.assertEquals("lihtjunit修改", productTypeService.queryRecord(id).getpDesc());
    }

    @Test
    public void deleteRecord() throws Exception {
        int id = productTypeService.insertRecord(productType);
        int count = productTypeService.deleteRecord(id);
        Assert.assertTrue(count == 1);
    }

    @Test
    public void queryList() throws Exception {
        ProductTypeForm form = new ProductTypeForm();
        form.setName("微");
        List<ProductType> list = productTypeService.queryList(form);
        Assert.assertTrue(list.size() > 0);
    }
}
