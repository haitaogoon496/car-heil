package com.mljr.heil.controller.calc;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.facade.calc.CalcFormulaParamsFacade;
import com.mljr.heil.form.CalcFormulaParamsForm;
import com.mljr.heil.vo.calc.CalcFormulaParamsVo;
import com.mljr.heil.vo.calc.CalcModelParamsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 公式参数关联 Controller
 * @author lingyu.shang
 */
@RestController
@RequestMapping("/calcFormulaParams")
@Api(description = "【公式参数关联】相关api",tags = "公式参数关联")
public class CalcFormulaParamsController {

    @Autowired
    private CalcFormulaParamsFacade calcFormulaParamsFacade;

    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<CalcFormulaParamsVo>>
     */
    @ApiOperation("【公式参数关联】查询列表")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<CalcFormulaParamsVo>> loadRecords(@RequestBody PageForm<CalcFormulaParamsForm> form){
        return this.calcFormulaParamsFacade.loadRecords(form);
    }

    /**
     * 查询公式关联/未关联参数列表
     * @param formulaCode
     * @return Result<Map<String, List<CalcFormulaParamsVo>>>
     */
    @ApiOperation("【公式参数关联】查询公式关联/未关联参数列表")
    @RequestMapping(value = "/queryFormulaCodeParamsList/{formulaCode}",method = {RequestMethod.GET})
    public Result<Map<String, List<CalcModelParamsVo>>> queryFormulaCodeParamsList(@PathVariable String formulaCode){
        return this.calcFormulaParamsFacade.queryFormulaCodeParamsList(formulaCode);
    }

    /**
     * 保存公式合法参数配置
     * @param form
     * @return
     */
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    @ApiOperation("【公式参数关联】保存公式合法参数配置")
    public Result<String> saveRecord(@RequestBody CalcFormulaParamsForm form) {
        return this.calcFormulaParamsFacade.saveRecord(form);
    }



}
