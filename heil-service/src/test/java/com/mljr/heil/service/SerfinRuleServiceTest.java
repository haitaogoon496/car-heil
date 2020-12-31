package com.mljr.heil.service;

import com.alibaba.fastjson.JSONObject;
import com.lyqc.base.page.PageForm;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.entity.SerFinRule;
import com.mljr.heil.form.SerFinRuleForm;
import com.mljr.heil.service.rule.SerFinRuleService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Date : 2018/3/15$ 18:00$
 * @Author : liht
 */
public class SerfinRuleServiceTest extends AbstractTest {

    @Resource
    SerFinRuleService serFinRuleService;

    static SerFinRule serFinRule;

    @BeforeClass
    public static void beforeClass() {
        String json = "{\n" +
                "\"rateLevel\":\"1\",\n" +
                "  \"beginDate\": \"2018-02-10 08:07:58\",\n" +
                "  \"loanRate\":\"50\",\n" +
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
                "  \"remarks\": \"liht测试添加\",\n" +
                "  \"rule1\": \"1\",\n" +
                "  \"ruleSeqName\": \"lihtjunit測試\",\n" +
                "  \"serFinFee\":\"500\",\n" +
                "  \"serFinRates\": [\n" +
                "        {\n" +
                "            \n" +
                "            \"serFinRate\": 30,\n" +
                "            \"serFinRebateRate\": 60\n" +
                "        },\n" +
                "        {\n" +
                "            \"serFinRate\": 60,\n" +
                "            \"serFinRebateRate\": 80\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        serFinRule = JSONObject.parseObject(json, SerFinRule.class);
    }

    @Test
    public void testInsert() {
        int id = serFinRuleService.insertRecord(serFinRule);
        Assert.assertTrue(id > 0);
    }

    @Test
    public void testDel() {
        int id = serFinRuleService.insertRecord(serFinRule);
        int count = serFinRuleService.deleteRecord(id);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testUpdate() {
        int id = serFinRuleService.insertRecord(serFinRule);
        serFinRule = serFinRuleService.queryRecord(id);
        serFinRule.setRuleSeqName("lihtjunit修改");
        serFinRuleService.updateRecord(serFinRule);
        Assert.assertEquals("lihtjunit修改", serFinRuleService.queryRecord(id).getRuleSeqName());
    }

    @Test
    public void testQueryRecord() {
        int id = serFinRuleService.insertRecord(serFinRule);
        serFinRule = serFinRuleService.queryRecord(id);
        Assert.assertTrue(serFinRule != null);

    }

    @Test
    public void testQueryPage() {
        SerFinRuleForm form = new SerFinRuleForm();
        form.setName("post");
        List<SerFinRule> list = serFinRuleService.queryByPage(new PageForm<>(form));
        Assert.assertTrue(list.size() > 0);
    }

}
