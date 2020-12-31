package com.mljr.heil.controller.rule;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.facade.rule.YanbaoDealerFacade;
import com.mljr.heil.vo.common.DealerVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TODO 待删除
 * @description: 第二/三年保险费规则 门店配置
 * @Date : 下午6:15 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
//@RestController
//@RequestMapping("/yanbaoDealer")
//@Api(description = "【第二/三年保险费规则-管理所属门店】相关api",tags = "第二/三年保险费规则-管理所属门店")
public class YanbaoDealerController {
    @Autowired
    private YanbaoDealerFacade yanbaoDealerFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<GpsRule>>
     */
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    @ApiOperation("【第二/三年保险费规则门店配置】查询列表")
    public Result<PageVO<DealerVo>> loadRecords(@RequestBody PageForm<DealerQueryForm> form){
        return this.yanbaoDealerFacade.loadRecords(form);
    }
    /**
     * 删除
     * @param record
     * @return Result<String>
     */
    @RequestMapping(value = "/deleteRecord",method = {RequestMethod.POST})
    @ApiOperation("【第二/三年保险费规则门店配置】删除记录")
    public Result<String> deleteRecord(@RequestBody BaseDealerRes record){
        return this.yanbaoDealerFacade.deleteRecord(record);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    @ApiOperation("【第二/三年保险费规则门店配置】添加记录")
    public Result<String> saveRecord(@RequestBody BaseDealerRes record){
        return this.yanbaoDealerFacade.saveRecord(record);
    }
}
