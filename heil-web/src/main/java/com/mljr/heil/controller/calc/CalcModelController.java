package com.mljr.heil.controller.calc;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.entity.CalcModel;
import com.mljr.heil.facade.calc.CalcModelFacade;
import com.mljr.heil.form.CalcModelForm;
import com.mljr.heil.form.CalcModelParamsForm;
import com.mljr.heil.form.ProductForm;
import com.mljr.heil.vo.calc.CalcModelParamsVo;
import com.mljr.heil.vo.calc.CalcModelVo;
import com.mljr.heil.vo.product.SimpleProductVo;
import com.mljr.heil.vo.common.SyArgControlVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 产品公式设置
 * @Date : 2018/3/30 17:27
 * @Author : lihaitao
 */
@RestController
@RequestMapping("/calcModel")
@Api(description = "【产品公式设置】相关api",tags = "产品公式设置")
public class CalcModelController {
    @Autowired
    private CalcModelFacade calcModelFacade;
    @Autowired
    private CommonComponent commonComponent;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<ProductVo>>
     */
    @ApiOperation("【产品公式设置】查询列表")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<CalcModelVo>> loadRecords(@RequestBody PageForm<CalcModelForm> form){
        return this.calcModelFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<GpsRule>
     */
    @ApiOperation("【产品公式设置】查询详情")
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    public Result<CalcModelVo> queryRecord(@PathVariable Integer id){
        return this.calcModelFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @ApiOperation("【产品公式设置】删除记录")
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.calcModelFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【产品公式设置】新增/修改记录")
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    public Result<String> saveRecord(@RequestBody CalcModel record){
        return this.calcModelFacade.saveRecord(record);
    }
    /**
     * 状态更新
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【产品公式设置】状态更新")
    @RequestMapping(value = "/modifyStatus",method = {RequestMethod.POST})
    public Result<String> modifyStatus(@RequestBody CalcModel record){
        return this.calcModelFacade.modifyStatus(record);
    }

    @ApiOperation("【产品公式配置】产品产品")
    @RequestMapping(value = "/querySimpleProductRecords",method = {RequestMethod.POST})
    public Result<PageVO<SimpleProductVo>> querySimpleProductRecords(@RequestBody PageForm<ProductForm> form){
        return calcModelFacade.querySimpleProductRecords(form);
    }

    @ApiOperation("【产品公式配置】获取规则类型请求url")
    @RequestMapping(value = "/getRuleTypeUrl/{ruleType}",method = {RequestMethod.GET})
    public Result<String> getRuleTypeUrl(@PathVariable String ruleType ){
        return calcModelFacade.queryRuleTypeUrl(ruleType);
    }

    /**
     *计算公式计算校验
     * @param exp 计算公式
     * @param formulaCode 公式名称
     * @return
     */
    @ApiModelProperty("计算公式计算校验")
    @RequestMapping(value = "/validateCalcModelExp",method = RequestMethod.POST)
    public Result<String> validteExp(@RequestParam("exp") String exp,String formulaCode) {
        return this.commonComponent.validateCalcModelExp(exp, formulaCode);
    }

    @ApiModelProperty("当公式名称改变时，费用规则做相应改变")
    @RequestMapping(value = "/getFeeRuleByFormulaCode", method = RequestMethod.GET)
    public Result<SyArgControlVo> getFeeRuleByFormulaCode(@RequestParam("formulaCode") String formulaCode) {
        return this.calcModelFacade.getFeeRuleByFormulaCode(formulaCode);
    }

    /**
     * 保存
     *
     * @param form
     * @return Result<String>
     */
    @ApiOperation("【产品计算模型参数】根据公式名称获取对应参数")
    @RequestMapping(value = "/getParamsByFormulaCode", method = {RequestMethod.POST})
    public Result<List<CalcModelParamsVo>> getParamsByFormulaCode(@RequestBody PageForm<CalcModelParamsForm> form) {
        return this.calcModelFacade.getParamByFormulaCode(form);
    }
}
