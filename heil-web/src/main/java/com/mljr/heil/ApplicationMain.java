package com.mljr.heil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description SpringBoot启动入口
 * @Date：Created in 下午10:40 2018/1/28
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.mljr"})
@PropertySource({"classpath:config.properties"})
@EnableSwagger2
public class ApplicationMain extends SpringBootServletInitializer {

    static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
        System.out.println("########Spring Boot start time:"+new Date()+"########");
        executorService.execute(()->{

        });
        SortedSet<String> set = new TreeSet<>();
        set.last();
    }

    @PostConstruct
    public void init() {
        System.out.println("ApplicationMain 类执行init 方法------------------------------");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        System.out.println("ApplicationMain类执行configure方法------------------------");
        return builder.sources(ApplicationMain.class);
    }


}
