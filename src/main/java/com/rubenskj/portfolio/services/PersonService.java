package com.rubenskj.portfolio.services;

import com.rubenskj.portfolio.dto.PersonDTO;
import com.rubenskj.portfolio.exception.NotFoundException;
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

    public Person getPersonById(String id) {
        return this.personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found with this id."));
    }

    public Person updatePersonById(String id, PersonDTO personDTO) {
        LOGGER.info("Updating person.");
        LOGGER.debug("Person id: {}", id);
        LOGGER.debug("PersonDTO: {}", personDTO);

        Person person = this.getPersonById(id);

        this.setPersonByDTO(person, personDTO);

        return this.personRepository.save(person);
    }

    private void setPersonByDTO(Person person, PersonDTO personDTO) {
        LOGGER.info("Updating fields");

        person.setDisplayedName(personDTO.getDisplayedName());
        person.setDescription(personDTO.getDescription());
    }

}
