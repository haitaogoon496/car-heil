package com.mljr.heil.service.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.entity.AccountDealer;
import com.mljr.heil.service.rule.AccountDealerService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @description:
 * @Date : 2018/3/16$ 15:56$
 * @Author : liht
 */
public class AccountDealerServiceImplTest extends AbstractTest{

    @Resource
    AccountDealerService accountDealerService;

    static AccountDealer accountDealer;

    @BeforeClass
    public static void before() {
        accountDealer = new AccountDealer();
        accountDealer.setRuleSeq(121);
        accountDealer.setDealerCode(2342343);

    }

    @Test
    public void queryByPage() throws Exception {
        DealerQueryForm form = new DealerQueryForm();
        form.setId(100);

        List<BaseDealerRes> list = accountDealerService.queryByPage(new PageForm<>(form));
        assertTrue(list.size()>0);
    }

    @Test
    public void insertRecord() throws Exception {
        int i = accountDealerService.insertRecord(accountDealer);
        accountDealerService.deleteRecord(accountDealer);
        assertTrue(i == 1);
    }



    @Test
    public void deleteRecord() throws Exception {
        accountDealerService.insertRecord(accountDealer);
        int i = accountDealerService.deleteRecord(accountDealer);
        assertTrue(i == 1);
    }

}