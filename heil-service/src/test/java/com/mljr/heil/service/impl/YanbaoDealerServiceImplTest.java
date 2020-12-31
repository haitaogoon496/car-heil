package com.mljr.heil.service.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.entity.YanbaoDealer;
import com.mljr.heil.service.rule.YanbaoDealerService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @description:
 * @Date : 2018/3/16$ 16:39$
 * @Author : liht
 */
public class YanbaoDealerServiceImplTest extends AbstractTest{
    @Resource
    YanbaoDealerService yanbaoDealerService;
    static YanbaoDealer yanbaoDealer;

    @BeforeClass
    public static void before() {
        yanbaoDealer = new YanbaoDealer();
        yanbaoDealer.setRuleSeq(121);
        yanbaoDealer.setDealerCode(2342343);

    }

    @Test
    public void queryByPage() throws Exception {
        DealerQueryForm form = new DealerQueryForm();
        form.setId(100);

        List<BaseDealerRes> list = yanbaoDealerService.queryByPage(new PageForm<>(form));
        assertTrue(list.size()>0);
    }

    @Test
    public void insertRecord() throws Exception {
        int i = yanbaoDealerService.insertRecord(yanbaoDealer);
        yanbaoDealerService.deleteRecord(yanbaoDealer);
        assertTrue(i == 1);
    }



    @Test
    public void deleteRecord() throws Exception {
        yanbaoDealerService.insertRecord(yanbaoDealer);
        int i = yanbaoDealerService.deleteRecord(yanbaoDealer);
        assertTrue(i == 1);
    }

}