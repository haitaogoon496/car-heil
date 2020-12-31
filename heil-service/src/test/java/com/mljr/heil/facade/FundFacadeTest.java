package com.mljr.heil.facade;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.AbstractTest;
import com.mljr.heil.entity.fund.Fund;
import com.mljr.heil.facade.fund.FundFacade;
import com.mljr.heil.form.fund.FundForm;
import com.mljr.heil.vo.fund.FundVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @Date : 2018/6/13 15:35
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public class FundFacadeTest extends AbstractTest{

    @Autowired
    FundFacade fundFacade;

    @Test
    public void loadRecordsTest(){
        PageForm<FundForm> form  = new PageForm<>();
        form.setLimit(2);
        FundForm fundForm = new FundForm();
        fundForm.setId(6);
        form.setForm(fundForm);
        Result<PageVO<FundVo>> result =  fundFacade.loadRecords(form);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void queryRecordTest(){
        Result<FundVo> vo =  fundFacade.queryRecord(1);
        System.out.println(vo);
    }

    @Test
    public void saveRecordTest(){
        Fund fund = new Fund();
        fund.setId(1);
        fund.setFundNo("1");
        fund.setFundName("暗钟明月不归来");
        fund.setRemark("清如水镜");

        fundFacade.saveRecord(fund);
    }

    @Test
    public void deleteRecordTest(){
        Result<String>  result= fundFacade.deleteRecord(1);
        System.out.println(result);
    }
}
