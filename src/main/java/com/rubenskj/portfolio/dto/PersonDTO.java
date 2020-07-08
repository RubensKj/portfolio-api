package com.rubenskj.portfolio.dto;

import com.rubenskj.portfolio.model.Person;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PersonDTO {

    private String id;

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

    public PersonDTO(String id, String displayedName, String description, List<String> projectsIds, List<String> certificationsIds) {
        this.id = id;
        this.displayedName = displayedName;
        this.description = description;
        this.projectsIds = projectsIds;
        this.certificationsIds = certificationsIds;
    }

    public PersonDTO(String displayedName, String description, List<String> projectsIds, List<String> certificationsIds) {
        this.displayedName = displayedName;
        this.description = description;
        this.projectsIds = projectsIds;
        this.certificationsIds = certificationsIds;
    }

    public static PersonDTO of(Person person) {
        return new PersonDTO(
                person.getId(),
                person.getDisplayedName(),
                person.getDescription(),
                person.getProjectsIds(),
                person.getCertificationsIds()
        );
    }

    public String getId() {
        return id;
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
