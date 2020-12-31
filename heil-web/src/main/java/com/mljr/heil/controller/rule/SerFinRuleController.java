package com.mljr.heil.controller.rule;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.entity.SerFinRule;
import com.mljr.heil.facade.rule.SerFinRuleFacade;
import com.mljr.heil.form.SerFinRuleForm;
import com.mljr.heil.vo.rule.SerFinRuleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 平台费规则配置
 * @Date : 2018/2/11 17:29
 * @Author : lihaitao
 */
@RestController
@RequestMapping("/serFinRule")
@Api(description = "【平台费规则配置】相关api",tags = "平台费规则配置")
public class SerFinRuleController {
    @Autowired
    private SerFinRuleFacade serFinRuleFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<SerFinRuleVo>>
     */
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    @ApiOperation("【平台费规则配置】查询列表")
    public Result<PageVO<SerFinRuleVo>> loadRecords(@RequestBody  PageForm<SerFinRuleForm> form){
        return this.serFinRuleFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<SerFinRuleVo>
     */
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    @ApiOperation("【平台费规则配置】查询详情")
    public Result<SerFinRuleVo> queryRecord(@PathVariable Integer id){
        return this.serFinRuleFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    @ApiOperation("【平台费规则配置】删除记录")
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.serFinRuleFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    @ApiOperation("【平台费规则配置】新增/修改记录")
    public Result<String> saveRecord(@RequestBody  SerFinRule record){
        return this.serFinRuleFacade.saveRecord(record);
    }
}
