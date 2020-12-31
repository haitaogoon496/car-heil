package com.mljr.heil.common.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE + 20)
public class MybatisConfig {
	private Logger log  = LoggerFactory.getLogger(this.getClass());
	
	private final String mapper = "com.mljr.heil.mapper";
	
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 30)
    //@DependsOn("sqlSessionFactory")
    public MapperScannerConfigurer mapperScannerConfigurer() {
    	log.info("MybatisConfig-mapperScannerConfigurerz注入");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //获取之前注入的beanName为sqlSessionFactory的对象
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //指定xml配置文件的路径
        mapperScannerConfigurer.setBasePackage(mapper);
        return mapperScannerConfigurer;
    }
}
