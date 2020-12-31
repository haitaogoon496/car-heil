package com.mljr.heil.controller.config;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.entity.PdTag;
import com.mljr.heil.facade.config.PdTagFacade;
import com.mljr.heil.form.PdTagForm;
import com.mljr.heil.vo.config.PdTagVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description 标签操作
 * @author zhaoxin
 * @date 2018/6/1 下午3:56
 **/
@RestController
@RequestMapping("/tag")
@Api(description = "【系统标签设置】相关api",tags = "系统标签设置")
public class TagController {

    @Autowired
    private PdTagFacade pdTagFacade;
    /**
     * 查询列表
     * @param form
     * @return Result<String>
     */
    @ApiOperation("【标签】查询详情")
    @RequestMapping(value = "/queryList",method = {RequestMethod.POST})
    public Result<PdTagVo> queryList(@RequestBody PdTagForm form){
        return this.pdTagFacade.queryList(form);
    }

    /**
     * 查询所有标签
     * @param form
     * @return Result<String>
     */
    @ApiOperation("【标签】查询")
    @RequestMapping(value = "/queryAllTag",method = {RequestMethod.POST})
    public Result<List<String>> queryAllTag(@RequestBody PdTagForm form){
        return this.pdTagFacade.queryAllTag(form);
    }

    /**
     * 保存
     * @param tag
     * @return Result<String>
     */
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    @ApiOperation("【标签】保存记录")
    public Result<String> saveRecord(@RequestBody PdTag tag){
        return this.pdTagFacade.saveRecord(tag);
    }

    /**
     * 分页加载数据
     * @param form
     * @return
     */
    @RequestMapping(value = "/loadRecords", method = {RequestMethod.POST})
    @ApiOperation("【标签】查询列表")
    public Result<PageVO<PdTagVo>> loadRecords(@RequestBody PageForm<PdTagForm> form) {
        return this.pdTagFacade.loadRecords(form);
    }

    /**
     * 批量删除标签
     * @param list
     * @return
     */
    @RequestMapping(value = "/batchDelete",method = {RequestMethod.POST})
    @ApiOperation("【标签】批量删除")
    public Result<String> batchDelete(@RequestBody List<PdTag> list) {
        return this.pdTagFacade.batchDelete(list);
    }

}
