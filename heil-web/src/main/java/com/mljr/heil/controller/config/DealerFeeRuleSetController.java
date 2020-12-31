package com.mljr.heil.controller.config;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.facade.config.DealerFeeRuleSetFacade;
import com.mljr.heil.form.DealerRuleSetForm;
import com.mljr.heil.param.config.BatchLinkParam;
import com.mljr.heil.vo.config.DealerFeeRuleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:门店费用规则配置
 * @Date : 2018/6/8 15:39
 * @Author : lihaitao
 */
@RestController
@RequestMapping("/dealerFeeRuleSet")
@Api(description = "【门店批量关联】相关api",tags = "门店关联设置")
public class DealerFeeRuleSetController {

    @Autowired
    private DealerFeeRuleSetFacade dealerFeeRuleSetFacade;
    /**
     * 设置关联
     * @param batchLinkParam
     * @return Result<DealerFeeRuleVo>
     */
    @ApiOperation("【门店批量关联】添加关联")
    @RequestMapping(value = "/addMapping",method = {RequestMethod.POST})
    public Result<DealerFeeRuleVo> setMapping(@RequestBody BatchLinkParam batchLinkParam){
        return this.dealerFeeRuleSetFacade.dealerFeeRuleSet(batchLinkParam);
    }
    /**
     * 解除关联
     * @param batchLinkParam
     * @return Result<DealerFeeRuleVo>
     */
    @ApiOperation("【门店批量关联】删除关联")
    @RequestMapping(value = "/delMapping",method = {RequestMethod.POST})
    public Result<DealerFeeRuleVo> delMapping(@RequestBody BatchLinkParam batchLinkParam){
        return this.dealerFeeRuleSetFacade.dealerFeeRuleDel(batchLinkParam);
    }

    /**
     *门店关联管理 begin
     */
    @ApiModelProperty(value = "查询当前门店绑定的规则")
    @RequestMapping(value = "/queryBindRulesForDealer",method = RequestMethod.POST)
    public Result<PageVO> queryBindRulesForDealer(@RequestBody PageForm<DealerRuleSetForm> form) {
        return this.dealerFeeRuleSetFacade.queryBindRulesForDealer(form);
    }

    @ApiModelProperty(value = "查询当前门店未绑定的规则")
    @RequestMapping(value = "/queryNoBindRulesForDealer",method = RequestMethod.POST)
    public Result<PageVO> queryNoBindRulesForDealer(@RequestBody PageForm<DealerRuleSetForm> form) {
        return this.dealerFeeRuleSetFacade.queryNoBindRulesForDealer(form);
    }
    @ApiModelProperty(value = "为当前门店配置费用规则")
    @RequestMapping(value = "/addRulesForDealer",method = RequestMethod.POST)
    public Result<String> addRulesForDealer(@RequestBody DealerRuleSetForm form) {
        return this.dealerFeeRuleSetFacade.addRulesForDealer(form);
    }
    @ApiModelProperty(value = "为当前门店删除费用规则")
    @RequestMapping(value = "/delRulesForDealer",method = RequestMethod.POST)
    public Result<String> delRulesForDealer(@RequestBody DealerRuleSetForm form) {
        return this.dealerFeeRuleSetFacade.delRulesForDealer(form);
    }
    /**
     *门店关联管理 end
     */
}
