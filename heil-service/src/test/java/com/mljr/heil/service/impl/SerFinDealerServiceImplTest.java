package com.mljr.heil.service.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.entity.SerFinDealer;
import com.mljr.heil.service.rule.SerFinDealerService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @description:
 * @Date : 2018/3/16$ 16:36$
 * @Author : liht
 */
public class SerFinDealerServiceImplTest extends AbstractTest{

    @Resource
    SerFinDealerService serFinDealerService;
    static SerFinDealer serFinDealer;

    @BeforeClass
    public static void before() {
        serFinDealer = new SerFinDealer();
        serFinDealer.setRuleSeq(121);
        serFinDealer.setDealerCode(2342343);

    }

    @Test
    public void queryByPage() throws Exception {
        DealerQueryForm form = new DealerQueryForm();
        form.setId(100);

        List<BaseDealerRes> list = serFinDealerService.queryByPage(new PageForm<>(form));
        assertTrue(list.size()>0);
    }

    @Test
    public void insertRecord() throws Exception {
        int i = serFinDealerService.insertRecord(serFinDealer);
        serFinDealerService.deleteRecord(serFinDealer);
        assertTrue(i == 1);
    }



    @Test
    public void deleteRecord() throws Exception {
        serFinDealerService.insertRecord(serFinDealer);
        int i = serFinDealerService.deleteRecord(serFinDealer);
        assertTrue(i == 1);
    }

}