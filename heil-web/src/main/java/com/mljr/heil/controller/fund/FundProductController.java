package com.mljr.heil.controller.fund;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.facade.fund.FundProductFacade;
import com.mljr.heil.form.fund.FundProductForm;
import com.mljr.heil.vo.product.ProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 资金方--产品关系绑定 controller
 * @Date : 2018/6/12 20:06
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */

@RestController
@RequestMapping("/fundProduct")
@Api(description = "【资金方-配置产品】相关api",tags = "资金方-配置产品")
public class FundProductController {

    @Autowired
    private FundProductFacade fundProductFacade;

    /**
     * 分页查出当前资金方绑定产品列表
     * @param form
     * @return
     */
    @ApiOperation("【资金方-产品 绑定关系】查询d当前列表")
    @RequestMapping(value = "/loadBindRecords",method = {RequestMethod.POST})
    public Result<PageVO<ProductVo>> loadBindRecords(@RequestBody PageForm<FundProductForm> form) {
        return  fundProductFacade.loadBindRecords(form);
    }

    /**
     * 分页查出当前资金方尚未绑定产品列表
     * @param form
     * @return
     */
    @ApiOperation("【资金方-产品 绑定关系】查询详情")
    @RequestMapping(value = "/loadNotBindRecords",method = {RequestMethod.POST})
    public Result<PageVO<ProductVo>> loadNotBindRecords(@RequestBody PageForm<FundProductForm> form) {
        return fundProductFacade.loadNotBindRecords(form);
    }

}
