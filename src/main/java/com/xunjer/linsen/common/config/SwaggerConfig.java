package com.xunjer.linsen.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author linsen
 * @date 2020/3/18 14:07
 * @tips 明日复明日 明日何其多
 */

@EnableSwagger2
@ComponentScan(basePackages = {"com.xunjer.linsen.controller"})
@Configuration
public class SwaggerConfig{

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("临森后台接口文档")//标题
                .description("接口文档")//描述
                .termsOfServiceUrl("http://www.google.com.hk")
                .contact(new Contact("Devil", "https://blog.csdn.net/qq_36911145", "969430169@qq.com"))//作者信息
                .version("6.6.6")//版本号
                .build();
    }



}
