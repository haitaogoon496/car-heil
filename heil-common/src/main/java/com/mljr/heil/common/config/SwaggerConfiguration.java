package com.mljr.heil.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: Swagger UI 配置
 * @Date : 2018/9/16 上午11:36
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket calcApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("计算引擎中心相关API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mljr.heil.controller.calc"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("公共接口相关API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mljr.heil.controller.common"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket configApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("产品配置中心相关API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mljr.heil.controller.config"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket fundApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("资金方管理中心相关API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mljr.heil.controller.fund"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("车贷产品管理中心相关API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mljr.heil.controller.product"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket ruleApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("产品费用规则中心相关API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mljr.heil.controller.rule"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket rootApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("监控检查&系统登录相关API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mljr.heil.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("车金融产品管理平台")
                .description("车金融产品管理平台接口文档")
                .termsOfServiceUrl("直营：http://dev-zy-heil.mljr.com/ \n渠道： http://dev-qd-heil.mljr.com/")
                .contact("车金融技术")
                .version("1.0")
                .build();
    }
}
