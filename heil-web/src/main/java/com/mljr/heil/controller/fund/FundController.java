package com.mljr.heil.controller.fund;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.entity.fund.Fund;
import com.mljr.heil.facade.fund.FundFacade;
import com.mljr.heil.form.ProductForm;
import com.mljr.heil.form.fund.FundForm;
import com.mljr.heil.vo.product.ProductVo;
import com.mljr.heil.vo.fund.FundVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 资金方配置 controller
 * @Date : 2018/6/12 20:06
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */

@RestController
@RequestMapping("/fund")
@Api(description = "【资金方管理】相关api",tags = "资金方管理")
public class FundController {

    @Autowired
    private FundFacade fundFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<FundVo>>
     */
    @ApiOperation("【资金方配置】查询列表")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<FundVo>> loadRecords(@RequestBody PageForm<FundForm> form){
        return this.fundFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<FundVo>
     */
    @ApiOperation("【资金方配置】查询详情")
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    public Result<FundVo> queryRecord(@PathVariable Integer id){
        return this.fundFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @ApiOperation("【资金方配置】删除记录")
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.fundFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【资金方配置】新增/修改记录")
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    public Result<String> saveRecord(@RequestBody Fund record){
        return this.fundFacade.saveRecord(record);
    }

    /**
     * 查询已经绑定的资金方的产品
     * @param form
     * @return
     */
    @ApiOperation("【资金方配置】查询已经绑定的资金方的产品")
    @RequestMapping("/queryBindProducts")
    public Result<PageVO<ProductVo>> queryBindProducts(@RequestBody PageForm<FundForm> form) {
        return this.fundFacade.queryBindProducts(form);
    }

    /**
     * 绑定资金方和产品的关系
     * @param productForm
     * @return
     */
    @ApiOperation("绑定/解绑资金方和产品关系")
    @RequestMapping("/bindProductForFund")
    public Result<Integer> bindProductForFund(@RequestBody ProductForm productForm) {
        return this.fundFacade.bindProductForFund(productForm);
    }

    /**
     * 查询没有绑定的资金方的产品
     * @param form
     * @return
     */
    @ApiOperation("【资金方配置】查询没有绑定的资金方的产品")
    @RequestMapping("/queryNotBindProducts")
    public Result<PageVO<ProductVo>> queryNotBindProducts(@RequestBody PageForm<FundForm> form) {
        return this.fundFacade.queryNotBindProducts(form);
    }

    /**
     * 状态更新
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【资金方配置】状态更新")
    @RequestMapping(value = "/modifyStatus",method = {RequestMethod.POST})
    public Result<String> modifyStatus(@RequestBody Fund record){
        return this.fundFacade.modifyStatus(record);
    }
}
