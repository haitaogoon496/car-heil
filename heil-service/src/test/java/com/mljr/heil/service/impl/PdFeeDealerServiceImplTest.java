package com.mljr.heil.service.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.entity.PdFeeDealer;
import com.mljr.heil.form.PdFeeDealerForm;
import com.mljr.heil.service.rule.PdFeeDealerService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @description:
 * @Date : 2018/3/16$ 16:24$
 * @Author : liht
 */
public class PdFeeDealerServiceImplTest extends AbstractTest{
    @Resource
    PdFeeDealerService pdFeeDealerService;

    static PdFeeDealer pdFeeDealer;

    @BeforeClass
    public static void before() {
        pdFeeDealer = new PdFeeDealer();
        pdFeeDealer.setResId(121);
        pdFeeDealer.setDealerCode(2342343);

    }

    @Test
    public void queryByPage() throws Exception {
        PdFeeDealerForm form = new PdFeeDealerForm();
        form.setId(100);

        List<PdFeeDealer> list = pdFeeDealerService.queryByPage(new PageForm<>(form));
        assertTrue(list.size()>0);
    }

    @Test
    public void insertRecord() throws Exception {
        int i = pdFeeDealerService.insertRecord(pdFeeDealer);
        assertTrue(i > 0);
    }



    @Test
    public void deleteRecord() throws Exception {
        int i = pdFeeDealerService.insertRecord(pdFeeDealer);
        int count = pdFeeDealerService.deleteRecord(i);
        assertTrue(count == 1);
    }

}