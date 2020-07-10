package com.rubenskj.portfolio.dto;

import com.rubenskj.portfolio.model.License;
import com.rubenskj.portfolio.model.Project;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectDTO {

    private String id;
    private List<String> images;
    private String name;
    private String fullName;
    private String language;
    private String description;
    private License license;
    private String projectUrl;
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

    public List<String> getImages() {
        return images;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }

    public License getLicense() {
        return license;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
