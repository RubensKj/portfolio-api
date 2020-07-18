package com.rubenskj.portfolio.security.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserCreationDTO {

    @NotNull(message = "E-mail cannot be null")
    @NotBlank(message = "E-mail cannot be blank")
    private String email;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotNull(message = "Authorities cannot be null")
    @NotEmpty(message = "Authorities cannot be empty")
    private List<String> authorities;

    public UserCreationDTO() {
    }

    public UserCreationDTO(@NotNull(message = "E-mail cannot be null") @NotBlank(message = "E-mail cannot be blank") String email, @NotNull(message = "Password cannot be null") @NotBlank(message = "Password cannot be blank") String password, @NotNull(message = "Authorities cannot be null") @NotEmpty(message = "Authorities cannot be empty") List<String> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getAuthorities() {
        return authorities;
    }
}
