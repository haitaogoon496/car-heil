package com.mljr.heil.controller.config;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.annotation.OvalValidator;
import com.mljr.heil.entity.AutoApprRule;
import com.mljr.heil.entity.AutoApprRuleKey;
import com.mljr.heil.facade.config.AutoApprRuleFacade;
import com.mljr.heil.form.AutoApprRuleForm;
import com.mljr.heil.vo.config.AutoApprRuleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 前置规则配置
 * @Date : 2018/3/30 17:27
 * @Author : lihaitao
 */
@RestController
@RequestMapping("/autoApprRule")
@Api(description = "【前置规则配置】相关api",tags = "前置规则配置")
public class AutoApprRuleController {
    @Autowired
    private AutoApprRuleFacade autoApprRuleFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<ProductVo>>
     */
    @ApiOperation("【前置规则配置】查询列表")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<AutoApprRuleVo>> loadRecords(@RequestBody PageForm<AutoApprRuleForm> form){
        return this.autoApprRuleFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<GpsRule>
     */
    @ApiOperation("【前置规则配置】查询详情")
    @RequestMapping(value = "/queryRecord",method = {RequestMethod.POST})
    public Result<AutoApprRuleVo> queryRecord(@RequestBody AutoApprRuleKey id){
        return this.autoApprRuleFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @ApiOperation("【前置规则配置】删除记录")
    @RequestMapping(value = "/deleteRecord",method = {RequestMethod.POST})
    public Result<String> deleteRecord(@RequestBody AutoApprRuleKey id){
        return this.autoApprRuleFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【前置规则配置】新增/修改记录")
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    @OvalValidator
    public Result<String> saveRecord(@RequestBody AutoApprRule record){
        return this.autoApprRuleFacade.saveRecord(record);
    }
    /**
     * 状态更新
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【前置规则配置】状态更新")
    @RequestMapping(value = "/modifyStatus",method = {RequestMethod.POST})
    public Result<String> modifyStatus(@RequestBody AutoApprRule record){
        return this.autoApprRuleFacade.modifyStatus(record);
    }
}
