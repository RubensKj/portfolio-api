package com.rubenskj.portfolio.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class ApplicationConfiguration {

    private static String PRODUCTION = "" +
            "▒█▀▀█ ▒█▀▀█ ▒█▀▀▀█ ▒█▀▀▄ ▒█░▒█ ▒█▀▀█ ░█▀▀█ ▒█▀▀▀█ \n" +
            "▒█▄▄█ ▒█▄▄▀ ▒█░░▒█ ▒█░▒█ ▒█░▒█ ▒█░░░ ▒█▄▄█ ▒█░░▒█ \n" +
            "▒█░░░ ▒█░▒█ ▒█▄▄▄█ ▒█▄▄▀ ░▀▄▄▀ ▒█▄▄█ ▒█░▒█ ▒█▄▄▄█ \n";

    public static void setProductionEnvironment() {
        System.out.println(PRODUCTION);

        System.out.println("Server running in production mode.");
    }
}
