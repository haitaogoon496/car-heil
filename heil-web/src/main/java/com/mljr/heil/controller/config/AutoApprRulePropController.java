package com.mljr.heil.controller.config;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.seig.dto.ExpressionCheckDTO;
import com.mljr.heil.entity.AutoApprRuleProp;
import com.mljr.heil.facade.config.AutoApprRulePropFacade;
import com.mljr.heil.form.AutoApprRulePropForm;
import com.mljr.heil.vo.config.AutoApprRulePropVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 前置规则-属性配置
 * @Date : 2018/3/30 17:27
 * @Author : lihaitao
 */
@RestController
@RequestMapping("/autoApprRuleProp")
@Api(description = "【前置规则属性配置】相关api",tags = "前置规则-属性配置")
public class AutoApprRulePropController {
    @Autowired
    private AutoApprRulePropFacade autoApprRulePropFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<ProductVo>>
     */
    @ApiOperation("【前置规则属性配置】查询列表")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<AutoApprRulePropVo>> loadRecords(@RequestBody PageForm<AutoApprRulePropForm> form){
        return this.autoApprRulePropFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<GpsRule>
     */
    @ApiOperation("【前置规则属性配置】查询详情")
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    public Result<AutoApprRulePropVo> queryRecord(@PathVariable Integer id){
        return this.autoApprRulePropFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @ApiOperation("【前置规则属性配置】删除记录")
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.autoApprRulePropFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【前置规则属性配置】新增/修改记录")
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    public Result<String> saveRecord(@RequestBody AutoApprRuleProp record){
        return this.autoApprRulePropFacade.saveRecord(record);
    }

    /**
     * 公式校验
     * @param dto
     * @return
     */
    @RequestMapping(path = "/checkExpression", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> checkExpression(@RequestBody ExpressionCheckDTO dto){
        return autoApprRulePropFacade.checkExpression(dto);
    }
    /**
     * 执行公式
     * @param dto
     * @return
     */
    @RequestMapping(path = "/execExpression", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> execExpression(@RequestBody ExpressionCheckDTO dto){
        return autoApprRulePropFacade.execExpression(dto);
    }

    /**
     * 状态更新
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【资金方配置】状态更新")
    @RequestMapping(value = "/modifyStatus",method = {RequestMethod.POST})
    public Result<String> modifyStatus(@RequestBody AutoApprRuleProp record){
        return this.autoApprRulePropFacade.modifyStatus(record);
    }

}
