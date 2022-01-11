package com.example.school.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
@EnableKnife4j
public class SwaggerConfig {

    @Order(value = 1)
    @Bean(value = "quickApi")
    public Docket groupRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.school.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo groupApiInfo(){
        return new ApiInfoBuilder()
                .title("API")
                .description("<div style='font-size:14px;color:red;'>RESTful APIs</div>")
                .contact(new Contact("RKY", "", ""))
                .version("1.0")
                .build();
    }
}
