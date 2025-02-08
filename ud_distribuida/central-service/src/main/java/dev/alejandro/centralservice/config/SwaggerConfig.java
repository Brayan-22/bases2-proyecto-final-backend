package dev.alejandro.centralservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI chapineroOpenApi(
             @Value("${springdoc.open-api.service.title}") String serviceTitle,
             @Value("${springdoc.open-api.service.version}") String serviceVersion,
             @Value("${springdoc.open-api.service.url}") String serviceUrl
    ) {
        return new OpenAPI()
                .servers(List.of(new Server().url(serviceUrl)))
                .info(new Info().version(serviceVersion).title(serviceTitle));
    }
}
