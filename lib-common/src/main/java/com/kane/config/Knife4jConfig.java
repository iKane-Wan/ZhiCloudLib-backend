package com.kane.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // 文档标题
                .info(new Info()
                        .title("图书管理系统接口文档")
                        // 文档描述
                        .description("基于 Spring Boot 3.x + Knife4j 的接口文档")
                        // 版本号
                        .version("1.0.0"));
    }
}
