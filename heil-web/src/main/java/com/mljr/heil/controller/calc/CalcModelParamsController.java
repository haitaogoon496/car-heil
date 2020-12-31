package com.mljr.heil.controller.calc;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.entity.CalcModelParams;
import com.mljr.heil.facade.calc.CalcModelParamsFacade;
import com.mljr.heil.form.CalcModelParamsForm;
import com.mljr.heil.vo.calc.CalcModelParamsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 产品计算模型参数
 * @Date : 2018/3/30 17:27
 * @Author : lihaitao
 */
@RestController
@RequestMapping("/calcModelParams")
@Api(description = "【公式参数设置】相关api",tags = "公式参数设置")
public class CalcModelParamsController {
    @Autowired
    private CalcModelParamsFacade calcModelParamsFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<ProductVo>>
     */
    @ApiOperation("【产品计算模型参数】查询列表")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<CalcModelParamsVo>> loadRecords(@RequestBody PageForm<CalcModelParamsForm> form){
        return this.calcModelParamsFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<GpsRule>
     */
    @ApiOperation("【产品计算模型参数】查询详情")
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    public Result<CalcModelParamsVo> queryRecord(@PathVariable Integer id){
        return this.calcModelParamsFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @ApiOperation("【产品计算模型参数】删除记录")
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.calcModelParamsFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【产品计算模型参数】新增/修改记录")
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    public Result<String> saveRecord(@RequestBody CalcModelParams record){
        return this.calcModelParamsFacade.saveRecord(record);
    }
    /**
     * 状态更新
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【产品计算模型参数】状态更新")
    @RequestMapping(value = "/modifyStatus",method = {RequestMethod.POST})
    public Result<String> modifyStatus(@RequestBody CalcModelParams record){
        return this.calcModelParamsFacade.modifyStatus(record);
    }
}
