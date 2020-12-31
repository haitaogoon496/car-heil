package com.mljr.heil.controller.rule;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.entity.AccountRule;
import com.mljr.heil.facade.rule.AccountRuleFacade;
import com.mljr.heil.form.AccountRuleForm;
import com.mljr.heil.vo.rule.AccountRuleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 
	* @ClassName: RateRuleController  
	* @Description: 账户管理费规则管理
	* @author lihaitao  
	* @date 2018年2月6日  
	*
 */
@RestController
@RequestMapping("/accountRule")
@Api(description = "【账号管理费规则配置】相关api",tags = "账号管理费规则配置")
public class AccountRuleController {
    @Autowired
    private AccountRuleFacade accountRuleFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<AccountRuleVo>>
     */
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    @ApiOperation("【账户管理费配置】查询列表")
    public Result<PageVO<AccountRuleVo>> loadRecords(@RequestBody  PageForm<AccountRuleForm> form){
        return this.accountRuleFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<AccountRuleVo>
     */
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    @ApiOperation("【账户管理费配置】查询详情")
    public Result<AccountRuleVo> queryRecord(@PathVariable Integer id){
        return this.accountRuleFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    @ApiOperation("【账户管理费配置】删除记录")
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.accountRuleFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    @ApiOperation("【账户管理费配置】新增/修改记录")
    public Result<String> saveRecord(@RequestBody AccountRule record){
        return this.accountRuleFacade.saveRecord(record);
    }
}
