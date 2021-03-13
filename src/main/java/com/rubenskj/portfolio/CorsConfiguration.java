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

    @Value("${com.rubens.cors}")
    public List<String> cors = new ArrayList<>();

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        CorsRegistration corsRegistration = registry
                .addMapping("/**");


        corsRegistration.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");


        if (!cors.isEmpty()) {
            corsRegistration.allowedOrigins(cors.toArray(new String[0]));
        }
    }
}
