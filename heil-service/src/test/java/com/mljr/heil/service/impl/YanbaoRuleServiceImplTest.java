package com.mljr.heil.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lyqc.base.page.PageForm;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.entity.YanbaoRule;
import com.mljr.heil.form.YanbaoRuleForm;
import com.mljr.heil.service.rule.YanbaoRuleService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @description:
 * @Date : 2018/3/16$ 15:19$
 * @Author : liht
 */
public class YanbaoRuleServiceImplTest extends AbstractTest{

    @Resource
    YanbaoRuleService yanbaoRuleService;

    static YanbaoRule yanbaoRule;

    @BeforeClass
    public static void before() {
        String json = "{\n" +
                "\t\"ruleSeqName\": \"liht第三年保费junit测试\",\n" +
                "\t\"isOld\": \"1\",\n" +
                "\t\"loanAmountMiin\": \"100\",\n" +
                "\t\"initScaleMin\": \"0.00\",\n" +
                "\t\"rLoanPeriods\": \"6\",\n" +
                "\t\"rule1\": \"1\",\n" +
                "\t\n" +
                "\t\"loanAmountMax\": \"200\",\n" +
                "\t\"initScaleMax\": \"0.00\",\n" +
                "\t\"isAllDealer\": \"1\",\n" +
                "\t\"proSeq\": \"1\",\n" +
                "\n" +
                "\t\"tcList\": [{\n" +
                "\t\t\"tcId\": 481,\n" +
                "\t\t\"tcZh\": \"动力智选\",\n" +
                "\t\t\"tcFee\": 1\n" +
                "\t}, {\n" +
                "\t\t\"tcId\": 482,\n" +
                "\t\t\"tcZh\": \"综合优选\",\n" +
                "\t\t\"tcFee\": 0\n" +
                "\t}, {\n" +
                "\t\t\"tcId\": 483,\n" +
                "\t\t\"tcZh\": \"全面精选\",\n" +
                "\t\t\"tcFee\": 0\n" +
                "\t}, {\n" +
                "\t\t\"tcId\": 547,\n" +
                "\t\t\"tcZh\": \"第二年保险\",\n" +
                "\t\t\"tcFee\": 0\n" +
                "\t}],\n" +
                "\t\"classify\": 3\n" +
                "}";

        yanbaoRule = JSONObject.parseObject(json, YanbaoRule.class);
    }

    @Test
    public void queryByPage() throws Exception {
        YanbaoRuleForm form = new YanbaoRuleForm();
        form.setName("liht");
        List<YanbaoRule> list = yanbaoRuleService.queryByPage(new PageForm<>(form));
        assertTrue(list.size() > 0);
    }

    @Test
    public void queryRecord() throws Exception {
        int id = yanbaoRuleService.insertRecord(yanbaoRule);
        yanbaoRule = yanbaoRuleService.queryRecord(id);
        assertTrue(yanbaoRule != null);
    }

    @Test
    public void insertRecord() throws Exception {
        int id = yanbaoRuleService.insertRecord(yanbaoRule);
        assertTrue(id > 0);
    }

    @Test
    public void updateRecord() throws Exception {
        int id = yanbaoRuleService.insertRecord(yanbaoRule);
        yanbaoRule = yanbaoRuleService.queryRecord(id);
        yanbaoRule.setRuleSeqName("lihtjunit测试修改");
        assertEquals("lihtjunit测试修改", yanbaoRuleService.queryRecord(id).getRuleSeqName());
    }

    @Test
    public void deleteRecord() throws Exception {
        int id = yanbaoRuleService.insertRecord(yanbaoRule);
        int count = yanbaoRuleService.deleteRecord(id);
        assertTrue(count == 1);
    }

}