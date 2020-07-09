package com.rubenskj.portfolio.dto;

import com.rubenskj.portfolio.model.Person;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PersonDTO {

    private String id;

    private String avatar;

    @NotNull(message = "Displayed Name cannot be null")
    @NotBlank(message = "Displayed Name cannot be empty")
    private String displayedName;

    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be empty")
    private String description;
    private List<String> projectsIds;
    private List<String> certificationsIds;

    public PersonDTO() {
    }

    public PersonDTO(String id, String avatar, @NotNull(message = "Displayed Name cannot be null") @NotBlank(message = "Displayed Name cannot be empty") String displayedName, @NotNull(message = "Description cannot be null") @NotBlank(message = "Description cannot be empty") String description, List<String> projectsIds, List<String> certificationsIds) {
        this.id = id;
        this.avatar = avatar;
        this.displayedName = displayedName;
        this.description = description;
        this.projectsIds = projectsIds;
        this.certificationsIds = certificationsIds;
    }

    public static PersonDTO of(Person person) {
        return new PersonDTO(
                person.getId(),
                person.getAvatar(),
                person.getDisplayedName(),
                person.getDescription(),
                person.getProjectsIds(),
                person.getCertificationsIds()
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

    public String getDescription() {
        return description;
    }

    public List<String> getProjectsIds() {
        return projectsIds;
    }

    public List<String> getCertificationsIds() {
        return certificationsIds;
    }
}
