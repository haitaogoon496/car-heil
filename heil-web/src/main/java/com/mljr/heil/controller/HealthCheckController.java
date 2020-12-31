package com.mljr.heil.controller;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.common.consts.HeilCode;
import com.mljr.heil.common.enums.HealthCheckIndexEnum;
import com.mljr.heil.form.PdHealthReportForm;
import com.mljr.heil.healthcheck.HealthCheckFacade;
import com.mljr.heil.healthcheck.HealthStrategy;
import com.mljr.heil.vo.healthCheck.HealthCheckMainPartVo;
import com.mljr.heil.vo.healthCheck.PdHealthReportVo;
import com.mljr.redis.service.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 健康检查
 * @Date : 2017/12/29 下午3:08
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@RestController
@Api(tags = "健康检查")
@Slf4j
public class HealthCheckController extends BaseController{

    @Autowired
    HealthStrategy healthStrategy;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    HealthCheckFacade healthCheckFacade;

    /**
     * 健康检查
     * @return
     */
    @RequestMapping(path = "/healthCheck", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> invoke() {
        return Result.suc();
    }

    /**
     * 生成健康检查结果
     * @return
     */
    @GetMapping("/generateHealthResult")
    @ResponseBody
    public Result<String> generateHealthResult(HttpServletRequest request) {
        Integer userId = getUserId(request);
        try {
            if (!redisUtil.setNx("healthCheckDataFlag" + userId, userId, 300)) {
                return Result.fail(HeilCode.E_500, "您的数据正在处理中~请稍等");
            }
            healthStrategy.generateHealthResult(userId);

        } catch (Exception e) {
            log.error("产品中心生成健康检查结果失败。",e);
        }
        return Result.suc("数据已在后台处理中，时间较长，请稍后在列表中查看结果");
    }

    /**
     * 获取健康检查结果列表
     * @return
     */
    @RequestMapping(path = "/loadHealthCheckDatas", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("【元数据健康检测】查询列表")
    public Result<PageVO<PdHealthReportVo>> loadHealthCheckDatas(@RequestBody PageForm<PdHealthReportForm> pageForm) {
        return healthStrategy.loadRecords(pageForm);
    }
    /**
     * 获取健康检查结果
     * @return
     */
    @GetMapping("/queryHealthCheckData/{id}")
    @ResponseBody
    @ApiOperation("【元数据健康检测】查询列表")
    public Result<PdHealthReportVo> queryHealthCheckData(@PathVariable Integer id) {
        return healthStrategy.queryRecord(id);
    }
    /**
     * 获取健康检查枚举
     * @return
     */
    @RequestMapping(path = "/getHealthCheckEnums", method = RequestMethod.GET)
    @ResponseBody
    public Result<Map<String,String>> getHealthCheckEnums() {
        Map<String,String> map = new LinkedHashMap<>();
        for(HealthCheckIndexEnum checkIndexEnum :HealthCheckIndexEnum.values()){
           map.put(checkIndexEnum.name(),checkIndexEnum.getDesc());
        }
        return Result.suc(map);
    }

    /**
     * 删除失效门店绑定的所有规则
     * @param healthCheckMainPartVo
     * @return
     */
    @PostMapping("/deleteDealerBindRules")
    @ResponseBody
    public Result<String> deleteDealerBindRules(@RequestBody HealthCheckMainPartVo<List<HealthCheckMainPartVo>> healthCheckMainPartVo){

        return healthCheckFacade.deleteDealerBindRules(healthCheckMainPartVo);
    }
}