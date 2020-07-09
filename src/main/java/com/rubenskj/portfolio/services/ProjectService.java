package com.rubenskj.portfolio.services;

import com.rubenskj.portfolio.dto.ProjectDTO;
import com.rubenskj.portfolio.model.Project;
import com.rubenskj.portfolio.repository.IProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

    private final IProjectRepository projectRepository;

    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project save(ProjectDTO projectDTO, List<MultipartFile> images) {
        LOGGER.info("Saving Project");
        LOGGER.debug("ProjectDTO: {}", projectDTO);

        Project project = this.createProjectByDTO(projectDTO);

        return this.projectRepository.save(project);
    }

    private Project createProjectByDTO(ProjectDTO projectDTO) {
        return new Project(
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

}
