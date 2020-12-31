package com.mljr.heil.controller.rule;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.entity.PdFeeProduct;
import com.mljr.heil.facade.rule.PdFeeProductFacade;
import com.mljr.heil.form.PdFeeProductForm;
import com.mljr.heil.vo.product.ProductVo;
import com.mljr.heil.vo.rule.PdFeeRuleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @description: 续保押金规则和产品绑定关系操作
 * @Date : 2018/6/25 14:56
 * @Author : lihaitao
 */
@RestController
@RequestMapping("/pdFeeProduct")
@Api(description = "【人身保险费/续保押金规则配置-配置产品相关api",tags = "人身保险费/续保押金规则配置-配置产品")
public class PdFeeProductController {

    @Autowired
    private PdFeeProductFacade pdFeeProductFacade;

    /**
     * 分页查出当前续保押金规则绑定产品列表
     * @param form
     * @return
     */
    @ApiOperation("【续保押金规则和产品 绑定关系】查询d当前列表")
    @RequestMapping(value = "/loadBindRecords",method = {RequestMethod.POST})
    public Result<PageVO<ProductVo>> loadBindRecords(@RequestBody PageForm<PdFeeProductForm> form) {
        return  pdFeeProductFacade.loadBindRecords(form);
    }

    /**
     * 分页查出当前续保押金规则尚未绑定产品列表
     * @param form
     * @return
     */
    @ApiOperation("【续保押金规则和产品 绑定关系】查询详情")
    @RequestMapping(value = "/loadNotBindRecords",method = {RequestMethod.POST})
    public Result<PageVO<ProductVo>> loadNotBindRecords(@RequestBody PageForm<PdFeeProductForm> form) {
        return pdFeeProductFacade.loadNotBindRecords(form);
    }


    /**
     * 分页查出当前续保押金规则绑定产品列表
     * @param form
     * @return
     */
    @ApiOperation("【续保押金规则和产品 绑定关系】查询d当前列表")
    @RequestMapping(value = "/loadBindPdFeeRule",method = {RequestMethod.POST})
    public Result<PageVO<PdFeeRuleVo>> loadBingPdFeeRule(@RequestBody PageForm<PdFeeProductForm> form) {
        return  pdFeeProductFacade.loadBindFeeRule(form);
    }

    /**
     * 分页查出当前续保押金规则尚未绑定产品列表
     * @param form
     * @return
     */
    @ApiOperation("【续保押金规则和产品 绑定关系】查询详情")
    @RequestMapping(value = "/loadNotBindPdFeeRule",method = {RequestMethod.POST})
    public Result<PageVO<PdFeeRuleVo>> loadNotPdFeeRule(@RequestBody PageForm<PdFeeProductForm> form) {
        return pdFeeProductFacade.loadNotBindFeeRule(form);
    }
    /**
     * 批量绑定产品
     * @param list
     * @return
     */
    @ApiOperation("【续保押金规则和产品 绑定关系】绑定产品")
    @RequestMapping(value = "/batchInsert",method = {RequestMethod.POST})
    public Result<String>  batchInsert(@RequestBody List<PdFeeProduct> list) {
        return  pdFeeProductFacade.batchInsert(list);
    }

    /**
     * 批量删除绑定关系
     * @param form
     * @return
     */
    @ApiOperation("【续保押金规则和产品 绑定关系】解除绑定")
    @RequestMapping(value = "/batchDelete",method = {RequestMethod.POST})
    public Result<String>  batchDelete(@RequestBody PdFeeProductForm form) {
        return  pdFeeProductFacade.batchDelete(form);
    }
}
