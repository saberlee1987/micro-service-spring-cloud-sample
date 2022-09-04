package com.saber.sample_service_provider.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${service.swagger.title}") String title, @Value("${service.swagger.description}") String description, @Value("${service.swagger.version}") String version) {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title(title)
                        .version(version)
                        .description(description)
                );
    }
}
