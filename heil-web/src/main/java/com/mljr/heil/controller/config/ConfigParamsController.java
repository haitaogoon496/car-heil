package com.mljr.heil.controller.config;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.annotation.OvalValidator;
import com.mljr.heil.entity.ConfigParams;
import com.mljr.heil.facade.common.ConfigParamsFacade;
import com.mljr.heil.form.ConfigParamsForm;
import com.mljr.heil.vo.config.ConfigParamsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 系统参数配置
 * @Date : 2018/4/12 18:27
 * @Author : lihaitao
 */
@RestController
@RequestMapping("/configParams")
@Api(description = "【全局参数配置】相关api",tags = "全局参数配置")
public class ConfigParamsController {
    @Autowired
    private ConfigParamsFacade configParamsFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<ProductVo>>
     */
    @ApiOperation("【系统参数配置】查询列表")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<ConfigParamsVo>> loadRecords(@RequestBody PageForm<ConfigParamsForm> form){
        return this.configParamsFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<GpsRule>
     */
    @ApiOperation("【系统参数配置】查询详情")
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    public Result<ConfigParamsVo> queryRecord(@PathVariable Integer id){
        return this.configParamsFacade.queryRecord(id);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @ApiOperation("【系统参数配置】删除记录")
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.configParamsFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【系统参数配置】新增/修改记录")
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    @OvalValidator
    public Result<String> saveRecord(@RequestBody ConfigParams record){
        return this.configParamsFacade.saveRecord(record);
    }

    /**
     * 状态更新
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【系统参数配置】状态更新")
    @RequestMapping(value = "/modifyStatus",method = {RequestMethod.POST})
    public Result<String> modifyStatus(@RequestBody ConfigParams record){
        return this.configParamsFacade.modifyStatus(record);
    }

    /**
     * 全量配置参数刷新到Redis 缓存中
     * @return Result<String>
     */
    @ApiOperation("【系统参数配置】FlushRedis")
    @RequestMapping(value = "/flushRedis",method = {RequestMethod.POST})
    public Result<String> flushRedis(){
        return this.configParamsFacade.flushRedis();
    }

    /**
     * 开关类型参数，开启/关闭
     * @param
     * @return Result<String>
     */
    @ApiOperation("【系统参数配置】状态更新")
    @RequestMapping(value = "/modifySwitch",method = {RequestMethod.POST})
    public Result<String> modifySwitch(@RequestBody ConfigParams record){
        return this.configParamsFacade.modifySwitch(record);
    }

}
