package com.mljr.heil.controller.product;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.facade.product.ProductDealerFacade;
import com.mljr.heil.vo.common.DealerVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TODO 待删除
 * @description:产品规则门店配置
 * @Date : 2018/2/8 15:48
 * @Author : lihaitao
 */
//@Api(description = "【产品管理所属门店】相关api", tags = "产品管理所属门店")
//@RestController
//@RequestMapping("/productDealer")
public class ProductDealerController {
    @Autowired
    private ProductDealerFacade productDealerFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<DealerVo>>
     */
    @ApiOperation("【产品规则门店配置】查询记录")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<DealerVo>> loadRecords(@ApiParam(value = "查询参数", required = false)
                                                    @RequestBody PageForm<DealerQueryForm> form){
        return this.productDealerFacade.loadRecords(form);
    }
    /**
     * 删除
     * @param record
     * @return Result<String>
     */
    @RequestMapping(value = "/deleteRecord",method = {RequestMethod.POST})
    @ApiOperation("【产品规则门店配置】删除记录")
    public Result<String> deleteRecord(@RequestBody BaseDealerRes record){
        return this.productDealerFacade.deleteRecord(record);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    @ApiOperation("【产品规则门店配置】添加记录")
    public Result<String> saveRecord(@RequestBody BaseDealerRes record){
        return this.productDealerFacade.saveRecord(record);
    }

    /**
     * 删除当前产品绑定的所有门店
      * @param
     * @return
     */
    @RequestMapping(value = "/deleteProductBindAllDealer/{id}",method = {RequestMethod.GET})
    @ApiOperation("【产品规则门店配置】添加记录")
    public Result<String> deleteProductBindAllDealer(@PathVariable Integer id){
        return this.productDealerFacade.deleteProductBindAllDealer(id);
    }
}
