package com.rubenskj.portfolio.dto;

import com.rubenskj.portfolio.model.Person;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PersonDTO {

    private String id;

    private String avatar;

    @NotNull(message = "Displayed Name cannot be null")
    @NotBlank(message = "Displayed Name cannot be empty")
    private String displayedName;

    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be empty")
    private String description;

    public PersonDTO() {
    }

    public PersonDTO(String id, String avatar, String displayedName, String description) {
        this.id = id;
        this.avatar = avatar;
        this.displayedName = displayedName;
        this.description = description;
    }

    public static PersonDTO of(Person person) {
        return new PersonDTO(
                person.getId(),
                person.getAvatar(),
                person.getDisplayedName(),
                person.getDescription()
        );
    }

    public String getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDisplayedName() {
        return displayedName;
    }

    public void setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
