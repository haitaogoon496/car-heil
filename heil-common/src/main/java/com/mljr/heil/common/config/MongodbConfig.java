package com.mljr.heil.common.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * @description: mongodb配置
 * @Date : 2019/3/21 下午4:43
 * @Author : 石冬冬-Seig Heil
 */
@Configuration
@EnableConfigurationProperties
public class MongodbConfig {
    @Value("${mongodb.uri}")
    String uri;
    @Value("${mongodb.database}")
    String database;

    @Bean
    public MongoClient mongoClient(){
        MongoClientOptions options = MongoClientOptions.builder().build();
        return new MongoClient(new MongoClientURI(uri, MongoClientOptions.builder(options)));
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        MongoDbFactory factory = new SimpleMongoDbFactory(mongoClient(),database);
        return new MongoTemplate(factory);
    }
}
