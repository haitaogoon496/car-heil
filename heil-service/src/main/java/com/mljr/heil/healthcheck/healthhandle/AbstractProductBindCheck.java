package com.mljr.heil.healthcheck.healthhandle;

import com.mljr.heil.entity.Product;
import com.mljr.heil.vo.healthCheck.HealthCheckMainPartVo;
import com.mljr.util.CollectionsTools;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @description:
 * @Date : 2018/10/18 18:29
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public abstract class AbstractProductBindCheck extends AbstractHealthCheck{

    public void resultAddCheckMap(List<Product> productList, List<HealthCheckMainPartVo> healthCheckMainPartVos, Consumer<HealthCheckMainPartVo> consumer){
        if (!CollectionsTools.isEmpty(productList)) {
            List<String> tempList = productList.stream().map(s -> String.valueOf(s.getpId())).collect(Collectors.toList());
            if(tempList != null && tempList.size() > 0) {
                HealthCheckMainPartVo healthCheckMainPartVo = new HealthCheckMainPartVo();
                healthCheckMainPartVo.setEvent(-1);
                consumer.accept(healthCheckMainPartVo);
                String result = String.join(",", tempList);
                healthCheckMainPartVo.setEventDetail(result);
                healthCheckMainPartVos.add(healthCheckMainPartVo);
            }
        }
    }
}
