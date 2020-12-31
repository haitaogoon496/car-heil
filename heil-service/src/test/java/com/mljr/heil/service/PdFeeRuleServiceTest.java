package com.mljr.heil.service;

import com.alibaba.fastjson.JSONObject;
import com.lyqc.base.page.PageForm;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.entity.PdFeeRule;
import com.mljr.heil.form.PdFeeRuleForm;
import com.mljr.heil.service.rule.PdFeeRuleService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Date : 2018/3/15$ 15:26$
 * @Author : liht
 */
public class PdFeeRuleServiceTest extends AbstractTest {

    @Resource
    PdFeeRuleService pdFeeRuleService;
    static PdFeeRule pdFeeRule;

    @BeforeClass
    public static void before() {
        String json = "{\n" +
                "  \"classify\":1,\n" +
                "  \"rateValue\": 5,\n" +
                "  \"loanPeriod\": \"6,12\",\n" +
                "  \"ruleName\": \"liht junit测试添加\"\n" +
                "}";
        pdFeeRule = JSONObject.parseObject(json, PdFeeRule.class);
    }

    @Test
    public void testInsert() {
        int id = pdFeeRuleService.insertRecord(pdFeeRule);
        Assert.assertTrue(id > 0);

    }

    @Test
    public void testDel() {
        int id = pdFeeRuleService.insertRecord(pdFeeRule);
        int count = pdFeeRuleService.deleteRecord(id);
        Assert.assertEquals(1, count);
    }

    @Test
    public void testUpdate() {
        int id = pdFeeRuleService.insertRecord(pdFeeRule);
        PdFeeRule pdFeeRule = pdFeeRuleService.queryRecord(id);
        pdFeeRule.setRuleName("lihtjunit 修改");
        pdFeeRuleService.updateRecord(pdFeeRule);
        Assert.assertEquals("lihtjunit 修改", pdFeeRuleService.queryRecord(id).getRuleName());
    }

    @Test
    public void testQueryRecord() {
        int id = pdFeeRuleService.insertRecord(pdFeeRule);
        PdFeeRule rule = pdFeeRuleService.queryRecord(id);
        Assert.assertTrue(rule != null);
    }

    @Test
    public void testQueryPage() {
        PdFeeRuleForm form = new PdFeeRuleForm();
        form.setId(10);

        List<PdFeeRule> list = pdFeeRuleService.queryByPage(new PageForm<>(form));
        Assert.assertTrue(list.size() > 0);
    }

}
