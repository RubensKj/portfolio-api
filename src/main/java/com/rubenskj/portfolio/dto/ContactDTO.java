package com.rubenskj.portfolio.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContactDTO {

    @NotNull(message = "Complete name cannot be null")
    @NotBlank(message = "Complete name cannot be empty")
    private String completeName;

    @NotNull(message = "E-mail cannot be null")
    @NotBlank(message = "E-mail cannot be empty")
    private String email;

    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be empty")
    private String description;

    public ContactDTO() {
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "completeName='" + completeName + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
