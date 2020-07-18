package com.rubenskj.portfolio.security.dto;

import java.time.LocalDateTime;

public class AuthDTO {

    private String uuid;
    private UserDTO userDTO;
    private LocalDateTime createdAt;

    public AuthDTO() {
    }

    public AuthDTO(String uuid, UserDTO userDTO, LocalDateTime createdAt) {
        this.uuid = uuid;
        this.userDTO = userDTO;
        this.createdAt = createdAt;
    }

    public String getUuid() {
        return uuid;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
