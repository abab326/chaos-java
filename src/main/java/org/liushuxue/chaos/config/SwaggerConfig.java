package org.liushuxue.chaos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI();
        Info info = new Info()
                .title("Spring Boot API")
                .description("Spring Boot API Reference for developers")
                .version("1.0.0");
        openAPI.info(info);
         return openAPI;
    }
}
