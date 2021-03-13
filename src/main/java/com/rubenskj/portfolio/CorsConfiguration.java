package com.rubenskj.portfolio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@EnableWebMvc
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Value("${com.rubens.cors.enable}")
    public Boolean CORS_ORIGINS_ENABLE;

    @Value("${com.rubens.cors}")
    public List<String> CORS_ORIGINS = new ArrayList<>();

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        CorsRegistration corsRegistration = registry
                .addMapping("/**");


        corsRegistration.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");


        if (CORS_ORIGINS_ENABLE && !CORS_ORIGINS.isEmpty()) {
            corsRegistration.allowedOrigins(CORS_ORIGINS.toArray(new String[0]));
        }
    }
}
