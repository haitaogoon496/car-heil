package com.mljr.heil.service.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.entity.GpsDealer;
import com.mljr.heil.service.rule.GpsDealerService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @description:
 * @Date : 2018/3/16$ 16:22$
 * @Author : liht
 */
public class GpsDealerServiceImplTest extends AbstractTest{

    @Resource
    GpsDealerService gpsDealerService;

    static GpsDealer gpsDealer;

    @BeforeClass
    public static void before() {
        gpsDealer = new GpsDealer();
        gpsDealer.setRuleSeq(121);
        gpsDealer.setDealerCode(2342343);

    }

    @Test
    public void queryByPage() throws Exception {
        DealerQueryForm form = new DealerQueryForm();
        form.setId(100);

        List<BaseDealerRes> list = gpsDealerService.queryByPage(new PageForm<>(form));
        assertTrue(list.size()>0);
    }

    @Test
    public void insertRecord() throws Exception {
        int i = gpsDealerService.insertRecord(gpsDealer);
        gpsDealerService.deleteRecord(gpsDealer);
        assertTrue(i == 1);
    }



    @Test
    public void deleteRecord() throws Exception {
        gpsDealerService.insertRecord(gpsDealer);
        int i = gpsDealerService.deleteRecord(gpsDealer);
        assertTrue(i == 1);
    }

}