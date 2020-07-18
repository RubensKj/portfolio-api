package com.rubenskj.portfolio.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rubenskj.portfolio.security.details.UserDetailsImpl;
import com.rubenskj.portfolio.security.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserDTO {

    private String id;

    @NotNull(message = "E-mail cannot be null")
    @NotBlank(message = "E-mail cannot be blank")
    private String email;

    @JsonIgnore
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    private String password;
    private List<String> authorities;
    private boolean isEnabled;

    public UserDTO() {
    }

    public UserDTO(String id, String email, String password, List<String> authorities, boolean isEnabled) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.isEnabled = isEnabled;
    }

    public UserDTO(String email, String password, List<String> authorities, boolean isEnabled) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.isEnabled = isEnabled;
    }

    public static UserDTO ofUserDetails(UserDetailsImpl userDetails) {
        return new UserDTO(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthoritiesInString(),
                userDetails.isEnabled()
        );
    }

    public static UserDTO of(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities(),
                user.isEnabled()
        );
    }

    public String getId() {
        return id;
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

    public boolean isEnabled() {
        return isEnabled;
    }
}
