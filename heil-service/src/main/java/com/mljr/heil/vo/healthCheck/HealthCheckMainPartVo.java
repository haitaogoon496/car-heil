package com.mljr.heil.vo.healthCheck;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:  健康检查结果统一vo
 * @Date : 2018/7/23 11:18
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthCheckMainPartVo<T> implements Serializable{

    private static final long serialVersionUID = 8671941099695871112L;
    /**
     * 事件枚举code
     */
    private Integer event;

    /**
     * 事件名称
     */
    private String eventName;
    /**
     * 事件详情
     */
    private T eventDetail;

}
