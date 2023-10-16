package com.example.pre_onboarding.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        Info info = new Info()
                .title("Wanted Pre onboarding Internship API Document.")
                .version("V0.0.1")
                .description("사전 과제 API 명세서입니다.");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
