package com.mljr.heil.service;

import com.mljr.heil.common.enums.HealthCheckIndexEnum;
import com.mljr.heil.vo.healthCheck.HealthCheckMainPartVo;

import java.util.List;
import java.util.Map;

/**
 * @description: 健康检查接口定义
 * @Date : 2018/7/23 11:55
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public interface HealthCheckService {
    /**
     * 校验每个健康检查策略，并生成结果
     */
    void doCheck();

    /**
     * 初始化每个检查策略的上下文。
     * @param healthCheckMap
     */
    void init(Map<HealthCheckIndexEnum,List<HealthCheckMainPartVo>> healthCheckMap);
}
