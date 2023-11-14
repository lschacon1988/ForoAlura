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
                .description("<h4>Descripción General</h4>\n" +
                                "<p>Esta API REST ha sido desarrollada en Java y Spring Boot, y " +
                                "tiene como objetivo simular el funcionamiento del foro Alura. " +
                                "Una de sus principales características es la gestión de la " +
                                "autenticación de usuarios mediante el uso de JSON Web Tokens " +
                                "(JWT).</p>\n" +
                                "\n" +
                                "<h4>Características Clave</h4>\n" +
                                "<ul>\n" +
                                "    <li>Registro y autenticación de usuarios: La API permite a " +
                                "los usuarios registrarse y autenticarse de manera segura " +
                                "utilizando JWT.</li>\n" +
                                "    <li>Pruebas Unitarias: Se han implementado pruebas unitarias" +
                                " exhaustivas utilizando JUnit y Mockito para asegurar el " +
                                "correcto funcionamiento de la API.</li>\n" +
                                "    <li>Base de Datos Relacional: La aplicación utiliza una base" +
                                " de datos relacional MySQL junto con JPA (Java Persistence API) " +
                                "para garantizar una gestión eficiente y flexible de la " +
                                "persistencia de datos. Esta elección permite una futura " +
                                "migración sencilla a otro sistema de gestión de bases de datos" +
                                ".</li>\n" +
                                "</ul>\n" +
                                "\n" +
                                "<h4>Estado Actual</h4>\n" +
                                "<p>La API se encuentra en una fase activa de desarrollo y mejora" +
                                " continua. A medida que evoluciona, se están agregando nuevas " +
                                "funcionalidades y se están realizando validaciones más " +
                                "exhaustivas de los datos para garantizar la calidad y la " +
                                "seguridad.</p>\n"
                        )
                .version("1.0");

    }

    @Bean
    public void message() {
        System.out.println("bearer is working");
    }
}
