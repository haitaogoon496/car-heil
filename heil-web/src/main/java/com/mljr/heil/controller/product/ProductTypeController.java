package com.mljr.heil.controller.product;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.entity.ProductType;
import com.mljr.heil.facade.product.ProductTypeFacade;
import com.mljr.heil.form.ProductTypeForm;
import com.mljr.heil.vo.product.ProductTypeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 产品分类管理
 * @Date : 下午6:15 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/productType")
@Api(description = "【产品分类管理】相关api",tags = "产品分类管理")
public class ProductTypeController {
    @Autowired
    private ProductTypeFacade productTypeFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<ProductTypeVo>>
     */
    @ApiOperation("【产品分类】查询列表")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<ProductTypeVo>> loadRecords(@RequestBody PageForm<ProductTypeForm> form){
        return this.productTypeFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<GpsRule>
     */
    @ApiOperation("【产品分类】查询详情")
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    public Result<ProductTypeVo> queryRecord(@PathVariable Integer id){
        return this.productTypeFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @ApiOperation("【产品分类】删除记录")
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.productTypeFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【产品分类】新增/修改记录")
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    public Result<String> saveRecord(@RequestBody ProductType record){
        return this.productTypeFacade.saveRecord(record);
    }

    /**
     * 查询列表
     * @param form
     * @return Result<String>
     */
    @ApiOperation("【产品分类】查询列表")
    @RequestMapping(value = "/queryList",method = {RequestMethod.POST})
    public Result<List<ProductTypeVo>> queryList(@RequestBody ProductTypeForm form){
        return this.productTypeFacade.queryList(form);
    }

    /**
     * 查询产品分类
     * 业务场景：用于相关模块下拉选择
     * @return Result<ProductTypeVo>
     */
    @ApiOperation("【产品分类】下拉列表")
    @RequestMapping(value = "/querySelectionList",method = {RequestMethod.GET})
    public Result<List<ProductTypeVo>> queryTypeNameList(){
        return this.productTypeFacade.querySelectionList();
    }
}
