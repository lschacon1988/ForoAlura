package com.alura.foro.APIRest.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
//@EnableSwagger2
public class SwaggerConfigure {

    @Bean
    public OpenAPI customOpenAPI() {
        System.out.println("jdkjs" + PathSelectors.any());
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                        .addServersItem(
                                new Server().url("https://foroalura-production.up.railway.app"))
                        .addServersItem(
                                new Server() .url("http://localhost:8080"));
    }




    @Bean
    public void message() {
        System.out.println("bearer is working");
    }
}
