package com.mljr.heil.controller.fund;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.entity.AutoApprRule;
import com.mljr.heil.entity.fund.PdFundRule;
import com.mljr.heil.facade.fund.PdFundRuleFacade;
import com.mljr.heil.form.fund.FundForm;
import com.mljr.heil.form.fund.PdFundRuleForm;
import com.mljr.heil.vo.fund.FundVo;
import com.mljr.heil.vo.fund.PdFundRuleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @description:资金方规则
 * @Date : 2018/6/13 11:23
 * @Author : lihaitao
 */
@RestController
@RequestMapping("/pdFundRule")
@Api(description = "【资金方准入规则】相关api",tags = "资金方准入规则")
public class PdFundRuleController {
    @Autowired
    private PdFundRuleFacade pdFundRuleFacade;

    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<PdFundRuleVo>>
     */
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    @ApiOperation("【资金方规则】查询列表")
    public Result<PageVO<PdFundRuleVo>> loadRecords(@RequestBody  PageForm<PdFundRuleForm> form){

        return this.pdFundRuleFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<PdFundRuleVo>
     */
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    @ApiOperation("【资金方规则】查询详情")
    public Result<PdFundRuleVo> queryRecord(@PathVariable Integer id){
        return this.pdFundRuleFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    @ApiOperation("【资金方规则】删除记录")
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.pdFundRuleFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    @ApiOperation("【资金方规则】新增/修改记录")
    public Result<String> saveRecord(@RequestBody  PdFundRule record){
        return this.pdFundRuleFacade.saveRecord(record);
    }
    /**
     * 状态更新
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【资金方规则】状态更新")
    @RequestMapping(value = "/modifyStatus",method = {RequestMethod.POST})
    public Result<String> modifyStatus(@RequestBody PdFundRule record){
        return this.pdFundRuleFacade.modifyStatus(record);
    }

    /**
     * 获取资金方
     * @param form
     * @return Result<List<FundVo>>
     */
    @ApiOperation("【资金方规则】获取资金方")
    @RequestMapping(value = "/getFundList",method = {RequestMethod.POST})
    public Result<List<FundVo>> getFundList(@RequestBody FundForm form){
        return this.pdFundRuleFacade.getFundList(form);
    }

}
