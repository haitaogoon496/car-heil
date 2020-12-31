package com.mljr.heil.controller.rule;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.entity.YanbaoRule;
import com.mljr.heil.facade.rule.YanbaoRuleFacade;
import com.mljr.heil.form.YanbaoRuleForm;
import com.mljr.heil.vo.rule.YanbaoRuleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 第二/三年保险费规则 配置
 * @Date : 下午6:15 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/yanbaoRule")
@Api(description = "【第二/三年保险费规则】相关api",tags = "第二/三年保险费规则配置")
public class YanbaoRuleController {
    @Autowired
    private YanbaoRuleFacade yanbaoRuleFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<GpsRule>>
     */
    @ApiOperation("【第二/三年保险费规则】查询列表")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<YanbaoRuleVo>> loadRecords(@RequestBody PageForm<YanbaoRuleForm> form){
        return this.yanbaoRuleFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<GpsRule>
     */
    @ApiOperation("【第二/三年保险费规则】查询详情")
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    public Result<YanbaoRuleVo> queryRecord(@PathVariable Integer id){
        return this.yanbaoRuleFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @ApiOperation("【第二/三年保险费规则】删除记录")
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.yanbaoRuleFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【第二/三年保险费规则】新增/修改记录")
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    public Result<String> saveRecord(@RequestBody YanbaoRule record){
        return this.yanbaoRuleFacade.saveRecord(record);
    }
}
