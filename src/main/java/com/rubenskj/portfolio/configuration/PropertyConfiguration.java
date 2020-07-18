package com.rubenskj.portfolio.configuration;

import com.rubenskj.portfolio.file.property.ImageProperty;
import com.rubenskj.portfolio.property.AdminProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        AdminProperty.class,
        ImageProperty.class
})
public class PropertyConfiguration {
}
