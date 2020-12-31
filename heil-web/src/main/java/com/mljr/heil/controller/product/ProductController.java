package com.mljr.heil.controller.product;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.entity.Product;
import com.mljr.heil.facade.product.ProductFacade;
import com.mljr.heil.form.ProductForm;
import com.mljr.heil.vo.common.ModifyStatusVo;
import com.mljr.heil.vo.product.ProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 车贷产品
 * @Date : 下午6:15 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/product")
@Api(description = "【车贷产品管理】相关api",tags = "车贷产品管理")
public class ProductController {
    @Autowired
    private ProductFacade productFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<ProductVo>>
     */
    @ApiOperation("【车贷产品】查询列表")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<ProductVo>> loadRecords(@RequestBody PageForm<ProductForm> form){
        return this.productFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<GpsRule>
     */
    @ApiOperation("【车贷产品】查询详情")
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    public Result<ProductVo> queryRecord(@PathVariable Integer id){
        return this.productFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @ApiOperation("【车贷产品】删除记录")
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.productFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【车贷产品】新增/修改记录")
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    public Result<String> saveRecord(@RequestBody Product record){
        return this.productFacade.saveRecord(record);
    }
    /**
     * 产品状态更新
     * @param param
     * @return Result<String>
     */
    @ApiOperation("【车贷产品】产品状态更新")
    @RequestMapping(value = "/modifyStatus",method = {RequestMethod.POST})
    public Result<String> modifyStatus(@RequestBody ModifyStatusVo param){
        return this.productFacade.modifyStatus(param);
    }
}
