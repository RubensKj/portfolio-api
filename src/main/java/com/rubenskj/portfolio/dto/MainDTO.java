package com.rubenskj.portfolio.dto;

import com.rubenskj.portfolio.model.Certification;

import java.util.List;

public class MainDTO {

    private PersonDTO personDTO;
    private List<ProjectDTO> projectsDTO;
    private List<Certification> certifications;

    public MainDTO() {
    }

    public MainDTO(PersonDTO personDTO, List<ProjectDTO> projectsDTO, List<Certification> certifications) {
        this.personDTO = personDTO;
        this.projectsDTO = projectsDTO;
        this.certifications = certifications;
    }

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    public void setPersonDTO(PersonDTO personDTO) {
        this.personDTO = personDTO;
    }

    public List<ProjectDTO> getProjectsDTO() {
        return projectsDTO;
    }

    public void setProjectsDTO(List<ProjectDTO> projectsDTO) {
        this.projectsDTO = projectsDTO;
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }
}
