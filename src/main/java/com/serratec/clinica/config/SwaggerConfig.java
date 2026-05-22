package com.serratec.clinica.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Clínica Popular Serratec")
                        .version("1.0")
                        .description("Sistema de gerenciamento de consultas, médicos e pacientes desenvolvida para o trabalho individual."));
    }
}