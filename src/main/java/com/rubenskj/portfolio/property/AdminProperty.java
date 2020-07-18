package com.rubenskj.portfolio.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "com.rubens.admin")
public class AdminProperty {

    @NotNull(message = "Admin email cannot be null")
    @NotBlank(message = "Admin email cannot be blank")
    private String email;

    @NotNull(message = "Admin password cannot be null")
    @NotBlank(message = "Admin password cannot be blank")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
