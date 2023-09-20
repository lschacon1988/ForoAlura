package com.alura.foro.APIRest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfigurations implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
<<<<<<< HEAD
                .allowedOrigins(
                        "https://foroalura-production.up.railway.app/**",
                        "http://foroalura-production.up.railway.app/**")
=======
                .allowedOrigins("http://foroalura-production.up.railway.app/**")
>>>>>>> e162cbeb6dad58dd31d38a54635657bbaa42b338
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }
}
