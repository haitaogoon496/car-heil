package com.mljr.heil.controller.rule;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.facade.rule.RateDealerFacade;
import com.mljr.heil.vo.common.DealerVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TODO 待删除
 * @description:利率规则门店配置
 * @Date : 2018/2/8 15:48
 * @Author : lihaitao
 */
//@Api(description = "【利率规则配置-管理所属门店】相关api", tags = "利率规则配置-管理所属门店")
//@RestController
//@RequestMapping("/rateDealer")
public class RateDealerController {
    @Autowired
    private RateDealerFacade rateDealerFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<DealerVo>>
     */
    @ApiOperation("【利率规则门店配置】查询记录")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<DealerVo>> loadRecords(@ApiParam(value = "查询参数", required = false)
                                                    @RequestBody PageForm<DealerQueryForm> form){
        return this.rateDealerFacade.loadRecords(form);
    }
    /**
     * 删除
     * @param record
     * @return Result<String>
     */
    @RequestMapping(value = "/deleteRecord",method = {RequestMethod.POST})
    @ApiOperation("【利率规则门店配置】删除记录")
    public Result<String> deleteRecord(@RequestBody BaseDealerRes record){
        return this.rateDealerFacade.deleteRecord(record);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    @ApiOperation("【利率规则门店配置】添加记录")
    public Result<String> saveRecord(@RequestBody BaseDealerRes record){
        return this.rateDealerFacade.saveRecord(record);
    }
}
