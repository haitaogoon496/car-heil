package com.mljr.heil.controller.rule;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.entity.PdFeeDealer;
import com.mljr.heil.facade.rule.PdFeeDealerFacade;
import com.mljr.heil.form.PdFeeDealerForm;
import com.mljr.heil.vo.common.DealerVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TODO 待删除
 * @description: 人身保险费规则配置 门店配置
 * @Date : 下午6:15 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
//@RestController
//@RequestMapping("/pdFeeDealer")
//@Api(description = "【人身保险费/续保押金规则配置-管理所属门店】相关api",tags = "人身保险费/续保押金规则配置-管理所属门店")
public class PdFeeDealerController {
    @Autowired
    private PdFeeDealerFacade pdFeeDealerFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<DealerVo>>
     */
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    @ApiOperation("【人身保险费规则门店配置】查询列表")
    public Result<PageVO<DealerVo>> loadRecords(@RequestBody PageForm<PdFeeDealerForm> form){
        return this.pdFeeDealerFacade.loadRecords(form);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    @ApiOperation("【人身保险费规则门店配置】删除记录")
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.pdFeeDealerFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    @ApiOperation("【人身保险费规则门店配置】添加记录")
    public Result<String> saveRecord(@RequestBody PdFeeDealer record){
        return this.pdFeeDealerFacade.saveRecord(record);
    }
}
