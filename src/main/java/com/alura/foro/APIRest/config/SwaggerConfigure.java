package com.alura.foro.APIRest.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
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
                                new Server() .url("http://localhost:8080"))
                .info(apiInfo());
    }


    private Info apiInfo() {

        return new Info()
                .title("API Rest foro Alura")
                .description("<h5>Es una api que simula el funcionamiento del foro Alura " +
                        "implementa JWT para la seguridad de acceso a las rutas protegidas, </br>" +
                        "pruebas unitarias con JUnit y mokito, usa una base de datos relacional " +
                        "MySql aun esta en desarrollo para completar funcionalidades y " +
                        "validaciones de algunos datos</h5>")
                .version("1.0");

    }

    @Bean
    public void message() {
        System.out.println("bearer is working");
    }
}
