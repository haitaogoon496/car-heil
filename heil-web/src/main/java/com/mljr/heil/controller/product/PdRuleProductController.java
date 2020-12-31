package com.mljr.heil.controller.product;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.facade.product.PdRuleProductFacade;
import com.mljr.heil.form.PdRuleProductForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 车贷产品管理-费用设置
 * @Date : 2018/8/6 15:47
 * @Author : lihaitao
 */
@RestController
@RequestMapping("/pdRuleProduct")
@Api(description = "【车贷产品管理-费用设置】相关api", value = "PdRuleProductController",tags = "车贷产品管理-费用设置")
public class PdRuleProductController {
    @Autowired
    private PdRuleProductFacade pdRuleProductFacade;


    /**
     * @description: 查询已关联
     * @Date : 2018/3/1 15:21
     * @Author : lihaitao
     */
    @RequestMapping(value = "/queryBindRules",method = RequestMethod.POST)
    @ApiOperation("查询已关联")
    public Result<PageVO> queryBindRules(@RequestBody PageForm<PdRuleProductForm> form){
        return this.pdRuleProductFacade.queryBindRules(form);
    }

    /**
     * @description: 查询未关联
     * @Date : 2018/3/1 15:21
     * @Author : lihaitao
     */
    @RequestMapping(value = "/queryNoBindRules",method = RequestMethod.POST)
    @ApiOperation("查询未关联")
    public Result<PageVO> queryNoBindRules(@RequestBody  PageForm<PdRuleProductForm> form){
        return this.pdRuleProductFacade.queryNoBindRules(form);
    }

    /**
     * @description: 添加关联
     * @Date : 2018/3/1 15:28
     * @Author : lihaitao
     */
    @ApiOperation("【添加关联】")
    @RequestMapping(value = "/addComponentProps",method = RequestMethod.POST)
    public Result<String> addComponentProps(@RequestBody PdRuleProductForm form){
        return this.pdRuleProductFacade.addComponentProps(form);
    }

    /**
     * @description: 删除关联
     * @Date : 2018/3/1 15:28
     * @Author : lihaitao
     */
    @ApiOperation("【删除关联】")
    @RequestMapping(value = "/delComponentProps",method = RequestMethod.POST)
    public Result<String> delComponentProps(@RequestBody  PdRuleProductForm from){
        return this.pdRuleProductFacade.delComponentProps(from);
    }
}
