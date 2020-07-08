package com.rubenskj.portfolio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Person {

    @Id
    private String id;

    private String displayedName;

    private String description;

    private List<String> projectsIds;

    private List<String> certificationsIds;

    public Person() {
    }

    public Person(String displayedName, String description, List<String> projectsIds, List<String> certificationsIds) {
        this.displayedName = displayedName;
        this.description = description;
        this.projectsIds = projectsIds;
        this.certificationsIds = certificationsIds;
    }

    public String getId() {
        return id;
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

    public List<String> getProjectsIds() {
        return projectsIds;
    }

    public void setProjectsIds(List<String> projectsIds) {
        this.projectsIds = projectsIds;
    }

    public List<String> getCertificationsIds() {
        return certificationsIds;
    }

    public void setCertificationsIds(List<String> certificationsIds) {
        this.certificationsIds = certificationsIds;
    }
}
