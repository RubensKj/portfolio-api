package com.rubenskj.portfolio.security.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginDTO {

    @NotNull(message = "E-mail cannot be null")
    @NotBlank(message = "E-mail cannot be blank")
    private String email;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(@NotNull(message = "E-mail cannot be null") @NotBlank(message = "E-mail cannot be blank") String email, @NotNull(message = "Password cannot be null") @NotBlank(message = "Password cannot be blank") String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
