package com.mljr.heil.service;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.page.PageForm;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.common.enums.HealthCheckIndexEnum;
import com.mljr.heil.entity.AccountRule;
import com.mljr.heil.form.AccountRuleForm;
import com.mljr.heil.healthcheck.HealthStrategy;
import com.mljr.heil.healthcheck.healthhandle.ProdDealerMatchSFRCheck;
import com.mljr.heil.healthcheck.healthhandle.ProductBindedSerFinMutexCheck;
import com.mljr.heil.vo.healthCheck.HealthCheckMainPartVo;
import com.mljr.heil.service.rule.AccountRuleService;
import com.mljr.util.TimeTools;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Date : 2018/3/14$ 18:22$
 * @Author : liht
 */
public class AccountRuleServiceTest extends AbstractTest{

    @Resource
    AccountRuleService accountRuleService;


    @Autowired
    ProductBindedSerFinMutexCheck productBindedSerFinMutexCheck;

    private static AccountRule accountRule;

    @Resource
    HealthStrategy healthStrategy;

    @Autowired
    ProdDealerMatchSFRCheck prodDealerMatchSFRCheck;
    @Test
    public void matchTest(){
        Map<HealthCheckIndexEnum,List<HealthCheckMainPartVo>> healthCheckMap = new HashMap<>();
        prodDealerMatchSFRCheck.init(healthCheckMap);
//        prodDealerMatchSFRCheck.doCheck();
        System.out.println(JSON.toJSONString(healthCheckMap));
    }

    @Test
    public void checkTest(){
        Map<HealthCheckIndexEnum,List<HealthCheckMainPartVo>> healthCheckMap = new HashMap<>();
        productBindedSerFinMutexCheck.init(healthCheckMap);
//        productBindedSerFinMutexCheck.doCheck();
        System.out.println(JSON.toJSONString(healthCheckMap));
    };

    @BeforeClass
    public static void beforeClass() {
        accountRule = new AccountRule();
        accountRule.setBeginDate(TimeTools.format4YYYYMMDD(new Date()));
        accountRule.setEndDate(TimeTools.format4YYYYMMDD(new Date()));
        accountRule.setCreateTime(TimeTools.createNowTime());
        accountRule.setUpdateTime(TimeTools.createNowTime());
        accountRule.setRuleSeqName("lihtjunit测试");
        accountRule.setIsAllDealer(0);
        accountRule.setIsOld("1");
        accountRule.setIsLcv("0");
        accountRule.setTopLimitFee(new BigDecimal("100"));
        accountRule.setTopLimitScale(1d);
        accountRule.setInitScaleMin(100d);
        accountRule.setInitScaleMax(200d);
        accountRule.setLoanAmountMiin(2000d);
        accountRule.setLoanAmountMax(40000d);
        accountRule.setVar(1);

    }

    @Test
    public void testQueryPage() {
        AccountRuleForm accountRuleForm = new AccountRuleForm();
        accountRuleForm.setId(10);
        List<AccountRule> accountRules = accountRuleService.queryByPage(new PageForm<>(accountRuleForm));

        Assert.assertTrue(accountRules.size() > 0);

    }

    @Test
    public void testQueryCount() {
        AccountRuleForm accountRuleForm = new AccountRuleForm();
        accountRuleForm.setId(10);
        int count = accountRuleService.queryCount(new PageForm<>(accountRuleForm));
        Assert.assertTrue(count > 0);
    }

    @Test
    @Transactional
    @Rollback(true
    )
    public void testInsert() {

        int index = accountRuleService.insertRecord(accountRule);
        Assert.assertTrue(index > 0);
    }

    @Test
    public void testUpdate() {
        AccountRule accountRule = accountRuleService.queryRecord(33);
        accountRule.setRuleSeqName("lihtjunit测试修改");
        int i = accountRuleService.updateRecord(accountRule);
        Assert.assertEquals(1,i);
    }

    @Test
    public void testDel() {
        accountRuleService.deleteRecord(34);
        AccountRule accountRule = accountRuleService.queryRecord(34);
        Assert.assertEquals(null,accountRule);
    }

    @Test
    public void testQueryRecord() {
        AccountRule accountRule = accountRuleService.queryRecord(33);
        Assert.assertTrue(accountRule != null);
    }

}
