package com.mljr.heil.controller.fund;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.entity.fund.PdFundRuleProp;
import com.mljr.heil.facade.fund.PdFundRulePropFacade;
import com.mljr.heil.form.fund.PdFundRulePropForm;
import com.mljr.heil.param.CityParam;
import com.mljr.heil.param.PdFundRulePropParam;
import com.mljr.heil.param.ProvinceParam;
import com.mljr.heil.vo.fund.PdFundRulePropVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:资金方规则属性controller
 * @Date : 2018/6/13 11:23
 * @Author : lihaitao
 */
@RestController
@RequestMapping("/pdFundRuleProp")
@Api(description = "【资金方准入规则-属性配置】相关api",tags = "资金方准入规则-属性配置")
public class PdFundRulePropController {
    @Autowired
    private PdFundRulePropFacade pdFundRulePropFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<PdFundRuleVo>>
     */
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    @ApiOperation("【资金方规则属性】查询列表")
    public Result<PageVO<PdFundRulePropVo>> loadRecords(@RequestBody  PageForm<PdFundRulePropForm> form){
        return this.pdFundRulePropFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<PdFundRuleVo>
     */
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    @ApiOperation("【资金方规则属性】查询详情")
    public Result<PdFundRulePropVo> queryRecord(@PathVariable Integer id){
        return this.pdFundRulePropFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    @ApiOperation("【资金方规则属性】删除记录")
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.pdFundRulePropFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    @ApiOperation("【资金方规则属性】新增/修改记录")
    public Result<String> saveRecord(@RequestBody PdFundRulePropParam record){
        return this.pdFundRulePropFacade.saveRecord(record);
    }

    /**
     * 获取省份
     * @return Result<String>
     */
    @RequestMapping(value = "/getProvinces",method = {RequestMethod.GET})
    @ApiOperation("【资金方规则属性】获取省份")
    public Result<List<ProvinceParam>> getProvinces(){
        return this.pdFundRulePropFacade.getProvinces();
    }
    /**
     * 获取省份对应的城市
     * @param provinceCode
     * @return Result<String>
     */
    @RequestMapping(value = "/getCities/{provinceCode}",method = {RequestMethod.GET})
    @ApiOperation("【资金方规则属性】获取省份所属的城市")
    public Result<List<CityParam>> getCities(@PathVariable String provinceCode){
        return this.pdFundRulePropFacade.getCities(provinceCode);
    }
}
