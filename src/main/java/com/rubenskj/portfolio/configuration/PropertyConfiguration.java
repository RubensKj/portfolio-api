package com.rubenskj.portfolio.configuration;

import com.rubenskj.portfolio.file.property.ImageProperty;
import com.rubenskj.portfolio.property.AdminProperty;
import com.rubenskj.portfolio.property.EmailProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        AdminProperty.class,
        EmailProperty.class,
        ImageProperty.class
})
public class PropertyConfiguration {
}
