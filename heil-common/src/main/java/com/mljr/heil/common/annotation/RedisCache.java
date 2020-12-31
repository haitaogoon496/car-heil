package com.mljr.heil.common.annotation;

import com.mljr.redis.enums.BuzType;
import com.mljr.redis.enums.FlushType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 基于Redis Cache的注解
 * @Date : 下午2:24 2018/4/23
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RedisCache {
    /**
     * Redis 业务类型枚举
     * @return
     */
    BuzType buzType() default BuzType.DEFAULT;
    /**
     * Redis 操作类型
     * @return
     */
    FlushType flushType() default FlushType.SET;
    /**
     * Redis 失效时间(单位：秒)
     * @return
     */
    long aliveTime() default 3600L;
}
