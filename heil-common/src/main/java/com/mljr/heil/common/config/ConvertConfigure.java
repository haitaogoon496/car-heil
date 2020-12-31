package com.mljr.heil.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

/** json 格式转换 (解决org.springframework.web.HttpMediaTypeNotAcceptableException: Could not findacceptable representation)
 * @description:
 * @Date : 2018/3/14 17:05
 * @Author : lihaitao
 */
@Configuration
public class ConvertConfigure {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        logger.info("自定义json转化器启动----");
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect);

        fastConverter.setFastJsonConfig(fastJsonConfig);

        HttpMessageConverter<?> converter = fastConverter;

        return new HttpMessageConverters(converter);

    }

}
