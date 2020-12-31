package com.mljr.heil.controller.rule;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.annotation.OvalValidator;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.entity.PdFeeRule;
import com.mljr.heil.facade.rule.PdFeeRuleFacade;
import com.mljr.heil.form.PdFeeRuleForm;
import com.mljr.heil.form.RuleStatusForm;
import com.mljr.heil.vo.rule.PdFeeRuleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 人身保险费规则配置
 * @Date : 下午6:15 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/pdFeeRule")
@Api(description = "【人身保险费/续保押金规则配置】相关api",tags = "人身保险费/续保押金规则配置")
public class PdFeeRuleController {
    @Autowired
    private PdFeeRuleFacade pdFeeRuleFacade;


    @Autowired
    private CommonComponent commonComponent;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<PdFeeRuleVo>>
     */
    @ApiOperation("【人身保险费规则配置】查询列表")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    @OvalValidator
    public Result<PageVO<PdFeeRuleVo>> loadRecords(@RequestBody PageForm<PdFeeRuleForm> form){
        return this.pdFeeRuleFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<GpsRule>
     */
    @ApiOperation("【人身保险费规则配置】查询详情")
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    public Result<PdFeeRuleVo> queryRecord(@PathVariable Integer id){
        return this.pdFeeRuleFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @ApiOperation("【人身保险费规则配置】删除记录")
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.pdFeeRuleFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【人身保险费规则配置】新增/修改记录")
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    @OvalValidator
    public Result<String> saveRecord(@RequestBody PdFeeRule record){
        return this.pdFeeRuleFacade.saveRecord(record);
    }

    @ApiOperation("【通用规则配置】修改规则状态")
    @RequestMapping(value = "/modifyStatus",method = {RequestMethod.POST})
    @OvalValidator
    public Result<String> modifyStatus(@RequestBody RuleStatusForm record){
        return this.pdFeeRuleFacade.modifyStatus(record);
    }

    /**
     *规则计算公式点击计算
     * @param exp 计算公式
     * @return
     */
    @ApiModelProperty("规则计算公式点击计算")
    @RequestMapping(value = "/validateExp",method = RequestMethod.POST)
    public Result<String> validteExp(@RequestParam("exp")  String exp) {
        return this.commonComponent.validateProductExp(exp);
    }

}
