package com.mljr.heil.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @description: anync 异步调用配置
 * @Date : 2018/3/6$ 13:36$
 * @Author : liht
 */
@Configuration
@EnableAsync
public class AsyncAnnotationConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("car-heil-MyAsync-");
        executor.setMaxPoolSize(10);
        executor.setCorePoolSize(5);
        executor.setQueueCapacity(500);
        // 设置拒绝策略
        executor.setRejectedExecutionHandler((runnable,poolExecutor) -> logger.warn("async线程池线程使用完毕,启动了拒绝策略...任务:{}",runnable) );
        logger.info("car-heil自定义线程池启动--");
        return executor;
    }
}
