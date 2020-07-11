package com.rubenskj.portfolio.controller;

import com.rubenskj.portfolio.dto.ProjectByUrlDTO;
import com.rubenskj.portfolio.dto.ProjectDTO;
import com.rubenskj.portfolio.services.ProjectService;
import com.rubenskj.portfolio.util.ParserUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/{personId}")
    public ProjectDTO create(@PathVariable("personId") String personId, @RequestParam(name = "images", required = false) List<MultipartFile> images, @Valid ProjectDTO projectDTO) {
        return ProjectDTO.of(this.projectService.save(personId, projectDTO, images));
    }

    @PostMapping("/provider/{personId}")
    public ProjectDTO createByGitlink(@PathVariable("personId") String personId, @Valid @RequestBody ProjectByUrlDTO projectByUrlDTO) {
        return ProjectDTO.of(this.projectService.createProjectByProjectUrl(personId, projectByUrlDTO));
    }

    @GetMapping("/{personId}")
    public List<ProjectDTO> getAllProjectsFromPerson(@PathVariable("personId") String personId) {
        return ParserUtil.parseProjectToDTO(this.projectService.getAllProjectFromPerson(personId));
    }

    @PutMapping("/{projectId}")
    public ProjectDTO updateProject(@PathVariable("projectId") String projectId, @Valid @RequestBody ProjectDTO projectDTO) {
        return ProjectDTO.of(this.projectService.updateByProjectId(projectId, projectDTO));
    }
}
