package com.rubenskj.portfolio.dto;

import javax.validation.constraints.NotBlank;

public class ProjectByUrlDTO {

    @NotBlank(message = "Name Provider cannot be blank")
    private String nameProvider;
    @NotBlank(message = "User cannot be blank")
    private String user;
    @NotBlank(message = "Repo name cannot be blank")
    private String repoName;

    public ProjectByUrlDTO() {
    }

    public ProjectByUrlDTO(String nameProvider, String user, String repoName) {
        this.nameProvider = nameProvider;
        this.user = user;
        this.repoName = repoName;
    }

    public String getNameProvider() {
        return nameProvider;
    }

    public String getUser() {
        return user;
    }

    public String getRepoName() {
        return repoName;
    }
}
