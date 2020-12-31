package com.mljr.heil.controller.rule;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.entity.GpsRule;
import com.mljr.heil.facade.rule.GpsRuleFacade;
import com.mljr.heil.form.GpsRuleForm;
import com.mljr.heil.vo.rule.GpsRuleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: GPS规则配置
 * @Date : 下午6:15 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/gpsRule")
@Api(description = "【GPS规则配置】相关api",tags = "GPS规则配置")
public class GpsRuleController {

    @Autowired
    private GpsRuleFacade gpsRuleFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<GpsRule>>
     */
    @ApiOperation("【GPS规则配置】查询列表")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<GpsRuleVo>> loadRecords(@RequestBody PageForm<GpsRuleForm> form){
        return this.gpsRuleFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<GpsRule>
     */
    @ApiOperation("【GPS规则配置】查询详情")
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    public Result<GpsRuleVo> queryRecord(@PathVariable Integer id){
        return this.gpsRuleFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @ApiOperation("【GPS规则配置】删除记录")
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.gpsRuleFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【GPS规则配置】新增/修改记录")
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    public Result<String> saveRecord(@RequestBody GpsRule record){
        return this.gpsRuleFacade.saveRecord(record);
    }
}
