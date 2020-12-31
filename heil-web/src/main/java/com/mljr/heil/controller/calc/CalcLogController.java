package com.mljr.heil.controller.calc;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.entity.CalcLog;
import com.mljr.heil.facade.calc.CalcLogFacade;
import com.mljr.heil.form.CalcLogForm;
import com.mljr.heil.vo.calc.CalcLogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 产品公式计算log
 * @Date : 2018/3/30 17:27
 * @Author : lihaitao
 */
@RestController
@RequestMapping("/calcLog")
@Api(description = "【计算链路分析】相关api",tags = "计算链路分析")
public class CalcLogController {
    @Autowired
    private CalcLogFacade calcLogFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<ProductVo>>
     */
    @ApiOperation("【产品公式计算log】查询列表")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<CalcLogVo>> loadRecords(@RequestBody PageForm<CalcLogForm> form){
        return this.calcLogFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<GpsRule>
     */
    @ApiOperation("【产品公式计算log】查询详情")
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    public Result<CalcLogVo> queryRecord(@PathVariable Integer id){
        return this.calcLogFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @ApiOperation("【产品公式计算log】删除记录")
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.calcLogFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【产品公式计算log】新增/修改记录")
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    public Result<String> saveRecord(@RequestBody CalcLog record){
        return this.calcLogFacade.saveRecord(record);
    }

}
