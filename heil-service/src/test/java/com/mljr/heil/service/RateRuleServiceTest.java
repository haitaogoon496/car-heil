package com.mljr.heil.service;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.entity.RateRule;
import com.mljr.heil.form.RateRuleForm;
import com.mljr.heil.service.rule.RateRuleService;
import com.mljr.util.TimeTools;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @Date : 2018/3/15$ 10:43$
 * @Author : liht
 */
public class RateRuleServiceTest extends AbstractTest{

    @Resource
    RateRuleService rateRuleService;

    static RateRule rateRule;

    @BeforeClass
    public static void before() {
        rateRule = new RateRule();
        rateRule.setVar(1);
        rateRule.setLoanAmountMax(10d);
        rateRule.setLoanAmountMiin(9d);
        rateRule.setInitScaleMin(1d);
        rateRule.setInitScaleMax(4d);
        rateRule.setIsLcv("0");
        rateRule.setHighRate(new BigDecimal("12"));
        rateRule.setIsOld("1");
        rateRule.setLoanRate(new BigDecimal("12"));
        rateRule.setRateLevel("2");
        rateRule.setBeginDate(TimeTools.format4YYYYMMDDHH00(new Date()));
        rateRule.setEndDate(TimeTools.format4YYYYMMDD(new Date()));
        rateRule.setrLoanPeriods("4");
        rateRule.setRuleSeqName("junit測試---");
    }

    @Test
    public void testQueryPage() {
        RateRuleForm form = new RateRuleForm();
        form.setId(12);

        List<RateRule> list = rateRuleService.queryByPage(new PageForm<>(form));
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testInsert() {
        int i = rateRuleService.insertRecord(rateRule);
        Assert.assertTrue(i > 0);

    }

    @Test
    public void testDel() {
        int i = rateRuleService.insertRecord(rateRule);
        int count = rateRuleService.deleteRecord(i);
        Assert.assertEquals(1, count);
    }

    @Test
    public void testUpdate() {
        RateRule rateRule = rateRuleService.queryRecord(87);
        rateRule.setRuleSeqName("rateRuleJunit测试");
        rateRuleService.updateRecord(rateRule);
        rateRule = rateRuleService.queryRecord(87);
        Assert.assertEquals("rateRuleJunit测试", rateRule.getRuleSeqName());
    }

    @Test
    public void testQueryRecord() {
        RateRule rateRule = rateRuleService.queryRecord(87);
        Assert.assertTrue(rateRule != null);
    }
}
