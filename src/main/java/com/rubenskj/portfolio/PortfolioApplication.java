package com.rubenskj.portfolio;

import com.kstruct.gethostname4j.Hostname;
import com.rubenskj.portfolio.configuration.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.util.Properties;

@SpringBootApplication
public class PortfolioApplication extends SpringBootServletInitializer implements EnvironmentAware {

    private static Class<PortfolioApplication> applicationClass = PortfolioApplication.class;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(PortfolioApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    @Override
    public void setEnvironment(Environment environment) {
        if (this.validateProfile()) {
            ApplicationConfiguration.setProductionEnvironment();
        }

        Properties props = new Properties();

        props.setProperty("env.computer", Hostname.getHostname());
        PropertiesPropertySource propertySource = new PropertiesPropertySource("ConputerConfig", props);

        if (environment instanceof StandardEnvironment) {
            ((StandardEnvironment) environment).getPropertySources().addFirst(propertySource);
        }
    }

    private boolean validateProfile() {
        return this.activeProfile != null && this.validateProduction();
    }

    private boolean validateProduction() {
        return this.activeProfile.equalsIgnoreCase("PRODUCTION");
    }
}
