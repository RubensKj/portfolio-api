package com.rubenskj.portfolio.services;

import com.rubenskj.portfolio.dto.ProjectDTO;
import com.rubenskj.portfolio.model.Project;
import com.rubenskj.portfolio.repository.IProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

    private final IProjectRepository projectRepository;

    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project save(ProjectDTO projectDTO) {
        LOGGER.info("Saving Project");
        LOGGER.debug("ProjectDTO: {}", projectDTO);

        return null;
    }

}
