package com.rubenskj.portfolio.dto;

import com.rubenskj.portfolio.model.License;
import com.rubenskj.portfolio.model.Project;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class ProjectDTO {

    private String id;
    private List<String> images;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "FullName cannot be null")
    @NotBlank(message = "FullName cannot be blank")
    private String fullName;

    @NotNull(message = "Language cannot be null")
    @NotBlank(message = "Language cannot be blank")
    private String language;

    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be blank")
    private String description;

    private License license;
    private String projectUrl;

    @NotNull(message = "GithubUrl cannot be null")
    @NotBlank(message = "GithubUrl cannot be blank")
    private String githubUrl;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public ProjectDTO() {
    }

    public ProjectDTO(String id, List<String> images, String name, String fullName, String language, String description, License license, String projectUrl, String githubUrl, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.images = images;
        this.name = name;
        this.fullName = fullName;
        this.language = language;
        this.description = description;
        this.license = license;
        this.projectUrl = projectUrl;
        this.githubUrl = githubUrl;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static ProjectDTO of(Project project) {
        return new ProjectDTO(
                project.getId(),
                project.getImages(),
                project.getName(),
                project.getFullName(),
                project.getLanguage(),
                project.getDescription(),
                project.getLicense(),
                project.getProjectUrl(),
                project.getGithubUrl(),
                project.getUpdatedAt(),
                project.getCreatedAt()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
