package com.rubenskj.portfolio.services;

import com.rubenskj.portfolio.dto.InformationDTO;
import com.rubenskj.portfolio.dto.PersonDTO;
import com.rubenskj.portfolio.model.Certification;
import com.rubenskj.portfolio.model.Person;
import com.rubenskj.portfolio.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rubenskj.portfolio.util.ParserUtil.parseCertificationToDTO;
import static com.rubenskj.portfolio.util.ParserUtil.parseProjectToDTO;

@Service
public class InformationService {

    private final PersonService personService;
    private final ProjectService projectService;
    private final CertificationService certificationService;

    public InformationService(PersonService personService, ProjectService projectService, CertificationService certificationService) {
        this.personService = personService;
        this.projectService = projectService;
        this.certificationService = certificationService;
    }

    public InformationDTO getInformationDTOByPersonId(String personId) {
        Person person = this.personService.findPersonById(personId);
        List<Project> allProjectFromPerson = this.projectService.getAllProjectFromPerson(personId);
        List<Certification> allCertificationFromPerson = this.certificationService.getAllCertificationFromPerson(personId);

        return new InformationDTO(
                PersonDTO.of(person),
                parseProjectToDTO(allProjectFromPerson),
                parseCertificationToDTO(allCertificationFromPerson)
        );
    }

    public InformationDTO getInformationDTOByPersonIdWithPinnedSorted(String personId) {
        Person person = this.personService.findPersonById(personId);
        List<Project> allProjectFromPerson = this.projectService.getAllProjectPinnedFromPerson(personId);
        List<Certification> allCertificationFromPerson = this.certificationService.getAllCertificationPinnedFromPerson(personId);

        return new InformationDTO(
                PersonDTO.of(person),
                parseProjectToDTO(allProjectFromPerson),
                parseCertificationToDTO(allCertificationFromPerson)
        );
    }
}
