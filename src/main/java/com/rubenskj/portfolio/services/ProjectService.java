package com.rubenskj.portfolio.services;

import com.rubenskj.portfolio.dto.ProjectByUrlDTO;
import com.rubenskj.portfolio.dto.ProjectDTO;
import com.rubenskj.portfolio.exception.NotFoundException;
import com.rubenskj.portfolio.exception.ProjectAlreadyExistsException;
import com.rubenskj.portfolio.file.service.ImageService;
import com.rubenskj.portfolio.model.Project;
import com.rubenskj.portfolio.payload.GitHubProject;
import com.rubenskj.portfolio.repository.IProjectRepository;
import com.rubenskj.portfolio.util.GitUrls;
import com.rubenskj.portfolio.util.GitUrls.GitProvider;
import com.rubenskj.portfolio.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.rubenskj.portfolio.PathTypeEnum.PROJECT_PATH_URI;
import static com.rubenskj.portfolio.util.HttpUtil.getUrlFormattedByProvider;

@Service
public class ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

    private final IProjectRepository projectRepository;
    private final ImageService imageService;

    public ProjectService(IProjectRepository projectRepository, ImageService imageService) {
        this.projectRepository = projectRepository;
        this.imageService = imageService;
    }

    public Project save(String personId, ProjectDTO projectDTO, List<MultipartFile> images) {
        LOGGER.info("Saving Project");
        LOGGER.debug("ProjectDTO: {}", projectDTO);

        Project project = this.createProjectByDTO(personId, projectDTO);

        this.handleImages(images, project);

        return this.projectRepository.save(project);
    }

    private Project createProjectByDTO(String personId, ProjectDTO projectDTO) {
        return new Project(
                personId,
                projectDTO.getName(),
                projectDTO.getFullName(),
                projectDTO.getLanguage(),
                projectDTO.getDescription(),
                projectDTO.getLicense(),
                projectDTO.getProjectUrl(),
                projectDTO.getGithubUrl(),
                projectDTO.getImages(),
                LocalDateTime.now()
        );
    }

    public List<Project> getAllProjectFromPerson(String personId) {
        return this.projectRepository.findAllByPersonId(personId);
    }

    public Project createProjectByProjectUrl(String personId, ProjectByUrlDTO projectByUrlDTO) {
        if (this.existsByNameOrFullName(projectByUrlDTO.getRepoName())) {
            throw new ProjectAlreadyExistsException("Project already exists with this name or full name.");
        }

        LOGGER.info("Getting provider");
        GitProvider provider = this.getProviderByName(projectByUrlDTO.getNameProvider());

        RestTemplate restTemplate = HttpUtil.getRestTemplate();

        LOGGER.info("Creating integration with provider");
        ResponseEntity<GitHubProject> providerProjectEntity = restTemplate.getForEntity(getUrlFormattedByProvider(provider, projectByUrlDTO), GitHubProject.class);

        if (!providerProjectEntity.hasBody()) {
            throw new IllegalArgumentException("Project not found in provider.");
        }

        GitHubProject providerProject = providerProjectEntity.getBody();

        Project project = this.createProjectByProvider(personId, providerProject);

        return this.projectRepository.save(project);
    }

    private boolean existsByNameOrFullName(String repoName) {
        if (repoName == null) {
            return false;
        }

        return this.projectRepository.existsByName(repoName);
    }

    private Project createProjectByProvider(String personId, GitHubProject providerProject) {
        return new Project(
                personId,
                providerProject.getName(),
                providerProject.getFull_name(),
                providerProject.getLanguage(),
                providerProject.getDescription(),
                providerProject.getLicense(),
                "",
                providerProject.getHtml_url(),
                new ArrayList<>(),
                LocalDateTime.now()
        );
    }

    private GitProvider getProviderByName(String nameProvider) {
        return GitUrls.getByNameOfProvider(nameProvider);
    }

    public Project updateByProjectId(String projectId, List<MultipartFile> images, ProjectDTO projectDTO) {
        Project project = this.findById(projectId);

        if (images != null) {
            this.handleImages(images, project);
        }

        this.updateProjectFromDTO(project, projectDTO);

        return this.projectRepository.save(project);
    }

    private void updateProjectFromDTO(Project project, ProjectDTO projectDTO) {
        if (isNotEmpty(projectDTO.getName())) {
            project.setName(projectDTO.getName());
        }

        if (isNotEmpty(projectDTO.getFullName())) {
            project.setFullName(projectDTO.getFullName());
        }

        if (isNotEmpty(projectDTO.getLanguage())) {
            project.setLanguage(projectDTO.getLanguage());
        }

        if (isNotEmpty(projectDTO.getDescription())) {
            project.setDescription(projectDTO.getDescription());
        }

        if (projectDTO.getLicense() != null && (isNotEmpty(projectDTO.getLicense().getKey()) && isNotEmpty(projectDTO.getLicense().getName()) && isNotEmpty(projectDTO.getLicense().getUrl()))) {
            project.setLicense(projectDTO.getLicense());
        }

        if (isNotEmpty(projectDTO.getProjectUrl())) {
            project.setProjectUrl(projectDTO.getProjectUrl());
        }

        if (isNotEmpty(projectDTO.getGithubUrl())) {
            project.setGithubUrl(projectDTO.getGithubUrl());
        }

        project.setUpdatedAt(LocalDateTime.now());
    }

    private boolean isNotEmpty(String name) {
        return !StringUtils.isEmpty(name);
    }

    private void handleImages(List<MultipartFile> images, Project project) {
        if (images != null && !images.isEmpty()) {
            String placeProjects = PROJECT_PATH_URI.getType();

            List<String> fileNames = this.imageService.saveImages(images, placeProjects);

            fileNames = fileNames.stream().map(fileName -> this.imageService.getDefaultUrl(fileName, placeProjects)).collect(Collectors.toList());

            project.getImages().addAll(fileNames);
        }
    }

    private Project findById(String projectId) {
        return this.projectRepository.findById(projectId).orElseThrow(() -> new NotFoundException("Project not found with this id. Id: " + projectId));
    }

    public void deleteById(String projectId) {
        this.projectRepository.deleteById(projectId);
    }
}
