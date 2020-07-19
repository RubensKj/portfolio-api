package com.rubenskj.portfolio;

import com.rubenskj.portfolio.configuration.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class PortfolioApplication extends SpringBootServletInitializer implements EnvironmentAware {

	private static Class<PortfolioApplication> applicationClass = PortfolioApplication.class;

	@Value("${spring.profiles.active}")
	private String activeProfile;

	public static void main(String[] args) {
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
	}

	private boolean validateProfile() {
		return this.activeProfile != null && this.validateProduction();
	}

	private boolean validateProduction() {
		return this.activeProfile.equalsIgnoreCase("PRODUCTION");
	}
}
