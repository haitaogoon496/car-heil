package com.mljr.heil.aop;

import com.alibaba.fastjson.JSON;
import com.mljr.heil.base.entity.BaseEntity;
import com.mljr.heil.common.annotation.RedisCache;
import com.mljr.heil.entity.ConfigParams;
import com.mljr.redis.enums.BuzType;
import com.mljr.redis.enums.FlushType;
import com.mljr.redis.service.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * @description: 基于@RedisCache注解的Advice
 * @Date : 2018/4/23 下午3:11
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Component
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RedisCacheAdvice {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("@within(com.mljr.heil.common.annotation.RedisCache)")
    private void withinPoint() {
    }

    @Pointcut("@annotation(com.mljr.heil.common.annotation.RedisCache)")
    private void annotationPoint() {
    }

    @Around("withinPoint() || annotationPoint()")
    public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> targetClass = this.parseTargetClass(joinPoint.getTarget());
        String className = targetClass.getName();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String target = className + "." + method.getName();
        Object proceedObject = null;
        Object[] args = null;
        try {
            args = joinPoint.getArgs();
            RedisCache annotation = method.getAnnotation(RedisCache.class);
            if(null != annotation){
                log.debug("[flushRedis]target:{},flushType:{},args:{}",target,annotation.flushType(),JSON.toJSON(args));
                flushRedis(args,annotation);
            }
            proceedObject = joinPoint.proceed();
        }catch (Exception e){
            log.warn("[flushRedis]exception,target:{},args:{}",target, JSON.toJSON(args),e);
        }
        return proceedObject;
    }

    protected Class<?> parseTargetClass(Object target) {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(target);
        if (targetClass == null) {
            targetClass = target.getClass();
        }
        return targetClass;
    }

    /**
     * 刷新Redis
     * @param args
     * @param annotation
     */
    private void flushRedis(Object[] args,RedisCache annotation){
        if(null != annotation){
            long aliveTime = annotation.aliveTime();
            String redisKey = getRedisKey(args,annotation);
            FlushType flushType = annotation.flushType();
            switch (flushType){
                case SET:
                    this.redisUtil.setWithPrefix(redisKey,args,aliveTime);
                    break;
                case DEL:
                    this.redisUtil.del(redisKey);
                    break;
                default:
                    this.redisUtil.setWithPrefix(redisKey,args,aliveTime);
                    break;
            }
        }
    }

    /**
     * 获取存储Redis的Key
     * @param args
     * @param annotation
     * @return
     */
    public String getRedisKey(Object[] args,RedisCache annotation){
        StringBuilder redisKey = new StringBuilder(annotation.buzType().getIndex());
        redisKey.append(":");
        BuzType buzType = annotation.buzType();
        Object object = args[0];
        switch (buzType){
            case DEFAULT:
                if(args[0] instanceof BaseEntity){
                    Integer id = ((BaseEntity)object).getId();
                    redisKey.append(id.toString());
                }
                break;
            case PD_CONFIG_PARAMS:
                if(args[0] instanceof ConfigParams){
                    redisKey.append(((ConfigParams)object).getParamKey());
                }
                break;
            default:
                if(args[0] instanceof BaseEntity){
                    Integer id = ((BaseEntity)object).getId();
                    redisKey.append(id.toString());
                }
                break;
        }
        return redisKey.toString();
    }

}
