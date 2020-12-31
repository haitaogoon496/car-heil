package com.mljr.heil.common.config;

import com.mljr.redis.config.RedisExtension;
import com.mljr.redis.enums.KeyPrefix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 配置Redis依赖相关参数
 * @see com.mljr.redis.service.RedisUtil
 * @see RedisExtension
 * @Date : 2018/12/13 下午12:15
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Configuration
public class RedisExtensionConfig {
    @Bean
    public RedisExtension getConfigExtend(){
        RedisExtension config = new RedisExtension();
        config.setKeyPrefix(KeyPrefix.CAR_PRODUCT);
        return config;
    }
}
