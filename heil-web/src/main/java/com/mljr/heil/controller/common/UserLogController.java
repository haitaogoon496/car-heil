package com.mljr.heil.controller.common;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.facade.common.UserLogFacade;
import com.mljr.heil.form.UserLogForm;
import com.mljr.heil.vo.common.UserLogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户操作日志
 * @Date : 2018/6/10 下午8:59
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Api(description = "【用户操作记录】相关api", tags = "用户操作记录")
@RestController
@RequestMapping("/userOperateLog")
public class UserLogController {

    @Autowired
    private UserLogFacade userLogFacade;

    /**
     * 加载数据
     * @param form
     * @return Result<PageVO<UserOperateLogVos>>
     */
    @ApiOperation("【用户操作日志】查询")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<UserLogVo>> loadRecords(@RequestBody PageForm<UserLogForm> form){
        return this.userLogFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<UserLogVo>
     */
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    @ApiOperation("【用户操作日志】查询详情")
    public Result<UserLogVo> queryRecord(@PathVariable Integer id){
        return this.userLogFacade.queryRecord(id);
    }
}
