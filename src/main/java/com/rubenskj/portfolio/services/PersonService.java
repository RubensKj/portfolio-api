package com.rubenskj.portfolio.services;

import com.rubenskj.portfolio.dto.PersonDTO;
import com.rubenskj.portfolio.model.Person;
import com.rubenskj.portfolio.repository.IPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    private final IPersonRepository personRepository;

    public PersonService(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(PersonDTO personDTO) {
        LOGGER.info("Saving person information");
        LOGGER.debug("PersonDTO: {}", personDTO);

        Person person = this.createPersonByDTO(personDTO);

        return this.personRepository.save(person);
    }

    private Person createPersonByDTO(PersonDTO personDTO) {
        return new Person(
                personDTO.getDisplayedName(),
                personDTO.getDescription(),
                personDTO.getProjectsIds(),
                personDTO.getCertificationsIds()
        );
    }

}
