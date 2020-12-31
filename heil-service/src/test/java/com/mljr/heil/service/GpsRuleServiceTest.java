package com.mljr.heil.service;

import com.alibaba.fastjson.JSONObject;
import com.lyqc.base.page.PageForm;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.entity.GpsRule;
import com.mljr.heil.form.GpsRuleForm;
import com.mljr.heil.service.rule.GpsRuleService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Date : 2018/3/15$ 15:08$
 * @Author : liht
 */
public class GpsRuleServiceTest extends AbstractTest {

    @Resource
    GpsRuleService gpsRuleService;

    static GpsRule gpsRule;

    @BeforeClass
    public static void before() {
        String json = "{\n" +
                "  \"beginDate\": \"2018-02-10 08:07:58\",\n" +
                "  \"endDate\": \"2020-02-10 08:07:58\",\n" +
                "  \"gpsCount\": 1,\n" +
                "  \"gpsFee\": 500,\n" +
                "  \"gpsPro\": \"1\",\n" +
                "  \"initScaleMax\": 100,\n" +
                "  \"initScaleMin\": 0,\n" +
                "  \"isAllDealer\": 0,\n" +
                "  \"isLcv\": \"1\",\n" +
                "  \"isOld\": \"1\",\n" +
                "  \"level\": 1,\n" +
                "  \"loanAmountMax\": 5000000,\n" +
                "  \"loanAmountMiin\": 0,\n" +
                "  \"proSeq\": 1,\n" +
                "  \"rLoanPeriods\": \"6,12\",\n" +
                "  \"rebate\": 300,\n" +
                "  \"remarks\": \"lihtjunit测试\",\n" +
                "  \"rule1\": \"1\",\n" +
                "  \"ruleSeq\": 0,\n" +
                "  \"ruleSeqName\": \"lihtjunit测试\"\n" +
                "}";
        gpsRule = JSONObject.parseObject(json, GpsRule.class);
    }

    @Test
    public void testQueryPage() {
        GpsRuleForm form = new GpsRuleForm();
        form.setId(10);

        List<GpsRule> list = gpsRuleService.queryByPage(new PageForm<>(form));
        Assert.assertTrue(list.size() > 0);

    }

    @Test
    public void testInsert() {
        int id = gpsRuleService.insertRecord(gpsRule);
        GpsRule rule = gpsRuleService.queryRecord(id);
        Assert.assertTrue(rule != null);
    }

    @Test
    public void testUpdate() {
        int id = gpsRuleService.insertRecord(gpsRule);
        GpsRule rule = gpsRuleService.queryRecord(id);
        rule.setRuleSeqName("liht junit修改");
        gpsRuleService.updateRecord(rule);
        Assert.assertEquals("liht junit修改", gpsRuleService.queryRecord(id).getRuleSeqName());
    }
}
