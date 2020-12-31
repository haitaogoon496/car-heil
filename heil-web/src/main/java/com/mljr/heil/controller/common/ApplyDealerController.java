package com.mljr.heil.controller.common;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.facade.config.ApplyDealerFacade;
import com.mljr.heil.vo.common.DealerVo;
import com.mljr.heil.vo.config.ApplyDealerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 管理所属门店
 * @Date : 2018/5/29 下午7:59
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Api(description = "【管理所属门店】相关api", tags = "管理所属门店")
@RestController
@RequestMapping("/applyDealer")
public class ApplyDealerController {

    @Autowired
    private ApplyDealerFacade applyDealerFacade;

    /**
     * 加载数据
     * @param form
     * @return Result<PageVO<DealerVo>>
     */
    @ApiOperation("【管理所属门店】查询")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<DealerVo>> loadRecords(@RequestBody PageForm<DealerQueryForm> form){
        return this.applyDealerFacade.loadRecords(form);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    @ApiOperation("【管理所属门店】保存")
    public Result<String> saveRecord(@RequestBody ApplyDealerVo record){
        return this.applyDealerFacade.saveRecord(record);
    }
}
