package com.mljr.heil.service.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.entity.RateDealer;
import com.mljr.heil.service.rule.RateDealerService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @description:
 * @Date : 2018/3/16$ 16:34$
 * @Author : liht
 */
public class RateDealerServiceImplTest extends AbstractTest{

    @Resource
    RateDealerService rateDealerService;
    static RateDealer rateDealer;

    @BeforeClass
    public static void before() {
        rateDealer = new RateDealer();
        rateDealer.setRuleSeq(121);
        rateDealer.setDealerCode(2342343);

    }

    @Test
    public void queryByPage() throws Exception {
        DealerQueryForm form = new DealerQueryForm();
        form.setId(100);

        List<BaseDealerRes> list = rateDealerService.queryByPage(new PageForm<>(form));
        assertTrue(list.size()>0);
    }

    @Test
    public void insertRecord() throws Exception {
        int i = rateDealerService.insertRecord(rateDealer);
        rateDealerService.deleteRecord(rateDealer);
        assertTrue(i == 1);
    }



    @Test
    public void deleteRecord() throws Exception {
        rateDealerService.insertRecord(rateDealer);
        int i = rateDealerService.deleteRecord(rateDealer);
        assertTrue(i == 1);
    }

}