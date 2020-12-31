package com.mljr.heil.controller.common;

import com.lyqc.base.common.Result;
import com.lyqc.base.enums.DictionaryBizCodeEnum;
import com.lyqc.base.re.SyArgControlRe;
import com.mljr.heil.component.DictionaryComponent;
import com.mljr.heil.vo.common.SyArgControlVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description: 数据字典Controller
 * @Date : 下午6:25 2018/3/3
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/dictionary")
@Api(description = "【数据字典管理】相关api",tags = "数据字典管理")
public class DictionaryController {
    @Autowired
    private DictionaryComponent dictionaryComponent;
    /**
     * 基于静态枚举的接口（查询枚举）
     * @param code
     * @return Result<List<SyArgControlVo>>
     */
    @RequestMapping(value = "/enums/{code}",method = {RequestMethod.GET})
    @ApiOperation("基于静态枚举的接口")
    public Result<List<SyArgControlVo>> enums(@PathVariable String code){
        return this.dictionaryComponent.enums(code);
    }

    /**
     * 批量查询基于静态枚举的接口（查询枚举）
     * @param enumList
     * @return Result<Map<String,List<SyArgControlVo>>>
     */
    @PostMapping("/enumList")
    @ApiOperation("批量查询基于静态枚举的接口")
    public Result<Map<String,List<SyArgControlVo>>> enumList(@RequestBody List<String> enumList){
        return this.dictionaryComponent.enumsList(enumList);
    }

    /**
     * 基于数据字典的接口（查询 pd_config_param）
     * @param code
     * @return Result<List<SyArgControlVo>>
     */
    @RequestMapping(value = "/dicts/{code}",method = {RequestMethod.GET})
    @ApiOperation("【数据字典】基于数据库表查询")
    public Result<List<SyArgControlRe>> dicts(@PathVariable DictionaryBizCodeEnum code) {
        return dictionaryComponent.dicts(code);
    }


    /**
     * 基于全局配置参数的接口
     * @param
     * @return Result<List<SyArgControlVo>>
     */
    @RequestMapping(value = "/paramList",method = {RequestMethod.POST})
    @ApiOperation("【数据字典】基于全局参数配置表查询")
    public Result<Map<String,Object>> params(@RequestBody List<String> keyList) {
        return dictionaryComponent.params(keyList);
    }
}
