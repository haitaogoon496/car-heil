package com.mljr.heil.healthcheck.healthhandle;

import com.mljr.heil.common.enums.HealthCheckIndexEnum;
import com.mljr.heil.service.HealthCheckService;
import com.mljr.heil.vo.healthCheck.HealthCheckMainPartVo;

import java.util.List;
import java.util.Map;

/**
 * @description:   健康检查策略抽象类，用于实例化上下文
 * @Date : 2018/7/23 18:15
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public abstract class AbstractHealthCheck implements HealthCheckService{

    Map<HealthCheckIndexEnum,List<HealthCheckMainPartVo>> healthCheckMap;

    @Override
    public void init(Map<HealthCheckIndexEnum,List<HealthCheckMainPartVo>> healthCheckMap){
        this.healthCheckMap = healthCheckMap;
    };

}
