package com.rubenskj.portfolio.payload;

import com.rubenskj.portfolio.model.License;

import java.time.LocalDateTime;

public class GitHubProject {

    private Long id;
    private String name;
    private String full_name;
    private String language;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private License license;
    private String html_url;

    public GitHubProject() {
    }

    public GitHubProject(Long id, String name, String full_name, String language, String description, LocalDateTime created_at, LocalDateTime updated_at, License license, String html_url) {
        this.id = id;
        this.name = name;
        this.full_name = full_name;
        this.language = language;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.license = license;
        this.html_url = html_url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
