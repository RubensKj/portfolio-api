package com.rubenskj.portfolio.dto;

import java.util.List;

public class InformationDTO {

    private PersonDTO personDTO;
    private List<ProjectDTO> projectsDTO;
    private List<CertificationDTO> certifications;

    public InformationDTO() {
    }

    public InformationDTO(PersonDTO personDTO, List<ProjectDTO> projectsDTO, List<CertificationDTO> certifications) {
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

    public List<CertificationDTO> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<CertificationDTO> certifications) {
        this.certifications = certifications;
    }
}
