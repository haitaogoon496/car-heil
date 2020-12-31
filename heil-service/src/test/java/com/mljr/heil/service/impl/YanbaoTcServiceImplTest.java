package com.mljr.heil.service.impl;

import com.mljr.heil.AbstractTest;
import com.mljr.heil.entity.YanbaoTc;
import com.mljr.heil.form.YanbaoTcForm;
import com.mljr.heil.service.rule.YanbaoTcService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @description:
 * @Date : 2018/3/16$ 15:29$
 * @Author : liht
 */
public class YanbaoTcServiceImplTest extends AbstractTest{

    @Resource
    YanbaoTcService yanbaoTcService;

    static YanbaoTc yanbaoTc;

    @BeforeClass
    public static void before() {
        yanbaoTc = new YanbaoTc();
        yanbaoTc.setUserName("liht");
        yanbaoTc.setTcId(21);
        yanbaoTc.setTcZh("liht综合优选");
        yanbaoTc.setTcFee(new BigDecimal("2000"));
        yanbaoTc.setRuleSeq(1212);

    }


    @Test
    public void queryRecord() throws Exception {
        int id = yanbaoTcService.insertRecord(yanbaoTc);
        YanbaoTc yanbaoTc = yanbaoTcService.queryRecord(id);
        assertTrue(yanbaoTc != null);
    }

    @Test
    public void insertRecord() throws Exception {
        int id = yanbaoTcService.insertRecord(yanbaoTc);
        assertTrue(id > 0);
    }

    @Test
    public void updateRecord() throws Exception {
        int id = yanbaoTcService.insertRecord(yanbaoTc);
        YanbaoTc yanbaoTc = yanbaoTcService.queryRecord(id);
        yanbaoTc.setTcFee(new BigDecimal("10"));
        yanbaoTcService.updateRecord(yanbaoTc);
        assertEquals(new BigDecimal("10.00"), yanbaoTcService.queryRecord(id).getTcFee());
    }

    @Test
    public void deleteRecord() throws Exception {
        int id = yanbaoTcService.insertRecord(yanbaoTc);
        int count = yanbaoTcService.deleteRecord(id);
        assertTrue(count == 1);
    }

    @Test
    public void queryList() throws Exception {
        yanbaoTcService.insertRecord(yanbaoTc);
        YanbaoTcForm form = new YanbaoTcForm();
        form.setRuleSeq(1212);
        List<YanbaoTc> list = yanbaoTcService.queryList(form);
        assertTrue(list.size() > 0);
    }

    @Test
    public void insertBatch() throws Exception {
        List<YanbaoTc> list = new ArrayList<>();
        list.add(yanbaoTc);
        list.add(yanbaoTc);
        yanbaoTcService.insertBatch(list);
        YanbaoTcForm form = new YanbaoTcForm();
        form.setRuleSeq(1212);
        List<YanbaoTc> listres = yanbaoTcService.queryList(form);
        assertTrue(listres.size() == 2);

    }

    @Test
    public void deleteByRuleSeq() throws Exception {
        int id = yanbaoTcService.insertRecord(yanbaoTc);
        yanbaoTcService.deleteByRuleSeq(1212);
        YanbaoTc yanbaoTc = yanbaoTcService.queryRecord(id);
        assertEquals(null, yanbaoTc);
    }

}