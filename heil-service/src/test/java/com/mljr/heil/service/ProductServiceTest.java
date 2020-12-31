package com.mljr.heil.service;

import com.alibaba.fastjson.JSONObject;
import com.lyqc.base.page.PageForm;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.entity.Product;
import com.mljr.heil.form.ProductForm;
import com.mljr.heil.service.product.ProductService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Date : 2018/3/15$ 17:18$
 * @Author : liht
 */
public class ProductServiceTest extends AbstractTest {

    @Resource
    ProductService productService;

    static Product product;

    @BeforeClass
    public static void before() {
        String json = "{\n" +
                "        \n" +
                "        \"year\": \"2015\",\n" +
                "        \"productCoe\": \"mlxsd\",\n" +
                "        \"pName\": \"lht测试\",\n" +
                "        \"statusDesc\": \"待提交\",\n" +
                "        \"type\": 2,\n" +
                "        \"beginTime\": \"2016-03-10\",\n" +
                "        \"endTime\": \"2020-03-10\",\n" +
                "        \"ver\": 1,\n" +
                "        \"status\": 6,\n" +
                "        \"desp\": \"lihtjunit测试\",\n" +
                "        \"isAllDealer\": \"1\",\n" +
                "        \"isBrand\": \"1\",\n" +
                "        \"isSeries\": \"1\",\n" +
                "        \"isStyles\": \"1\",\n" +
                "        \"userName\": \"liht\",\n" +
                "        \"userId\": \"2000000101\",\n" +
                "        \"carry\": \"1\",\n" +
                "        \"precisions\": 100,\n" +
                "        \"propVo\": null\n" +
                "      }";

        product = JSONObject.parseObject(json, Product.class);
    }

    @Test
    public void testInsert() {
        int id = productService.insertRecord(product);
        Assert.assertTrue(id > 0);
    }

    @Test
    public void testDel() {
        int id = productService.insertRecord(product);
        int count = productService.deleteRecord(id);
        Assert.assertEquals(1, count);
    }

    @Test
    public void testUpdate() {
        int id = productService.insertRecord(product);
        product = productService.queryRecord(id);
        product.setDesp("lihtjunit修改");
        productService.updateRecord(product);
        Assert.assertEquals("lihtjunit修改", productService.queryRecord(id).getDesp());
    }

    @Test
    public void testPageQuery() {
        ProductForm form = new ProductForm();
        form.setName("liht");
        List<Product> list = productService.queryByPage(new PageForm<>(form));
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testQueryRecord() {
        int id = productService.insertRecord(product);
        product = productService.queryRecord(id);
        Assert.assertTrue(product != null);
    }
}
