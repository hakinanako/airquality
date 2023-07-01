package com.neu.airquality.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SPRING_WEB)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nue.airquality"))
                .paths(PathSelectors.any())
                .build();
    }
    //配置文档信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("空气")
                .description("")
                .contact(new Contact("nue", "http://xxx.xxx.com/联系人访问链接", "联系人邮箱"))
                .version("0.1")
                .build();
    }
}
