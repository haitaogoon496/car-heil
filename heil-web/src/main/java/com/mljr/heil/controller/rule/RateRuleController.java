package com.mljr.heil.controller.rule;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.entity.RateRule;
import com.mljr.heil.facade.rule.RateRuleFacade;
import com.mljr.heil.form.RateRuleForm;
import com.mljr.heil.vo.rule.RateRuleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 
	* @ClassName: RateRuleController  
	* @Description: 利率配置
	* @author lihaitao  
	* @date 2018年2月6日  
	*
 */
@RestController
@RequestMapping("/rateRule")
@Api(description = "【利率规则配置】相关api",tags = "利率规则配置")
public class RateRuleController {
    @Autowired
    private RateRuleFacade rateRuleFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<RateRuleVo>>
     */
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    @ApiOperation("【利率配置】查询列表")
    public Result<PageVO<RateRuleVo>> loadRecords(@RequestBody  PageForm<RateRuleForm> form){
        return this.rateRuleFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<RateRuleVo>
     */
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    @ApiOperation("【利率配置】查询详情")
    public Result<RateRuleVo> queryRecord(@PathVariable Integer id){
        return this.rateRuleFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    @ApiOperation("【利率配置】删除记录")
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.rateRuleFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    @ApiOperation("【利率配置】新增/修改记录")
    public Result<String> saveRecord(@RequestBody  RateRule record){
        return this.rateRuleFacade.saveRecord(record);
    }
}
