package com.rubenskj.portfolio.configuration;

import com.rubenskj.portfolio.file.property.ImageProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        ImageProperty.class
})
public class PropertyConfiguration {
}
