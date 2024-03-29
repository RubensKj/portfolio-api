package com.rubenskj.portfolio.services;

import com.rubenskj.portfolio.dto.PersonDTO;
import com.rubenskj.portfolio.exception.NotFoundException;
import com.rubenskj.portfolio.file.service.ImageService;
import com.rubenskj.portfolio.model.Person;
import com.rubenskj.portfolio.repository.IPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static com.rubenskj.portfolio.enums.PathTypeEnum.PERSON_PATH_URI;

@Service
public class PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    private final IPersonRepository personRepository;
    private final ImageService imageService;
    private final HttpServletRequest request;

    public PersonService(IPersonRepository personRepository, ImageService imageService, HttpServletRequest request) {
        this.personRepository = personRepository;
        this.imageService = imageService;
        this.request = request;
    }

    public Person save(PersonDTO personDTO, MultipartFile avatar) {
        LOGGER.info("Saving person information");
        LOGGER.debug("PersonDTO: {}", personDTO);

        String avatarFileName = this.imageService.saveImage(avatar, PERSON_PATH_URI.getType());

        String urlImage = this.parseUrlAvatarByFileName(avatarFileName);

        personDTO.setAvatar(urlImage);

        Person person = this.createPersonByDTO(personDTO);

        return this.personRepository.save(person);
    }

    private Person createPersonByDTO(PersonDTO personDTO) {
        return new Person(
                personDTO.getAvatar(),
                personDTO.getDisplayedName(),
                personDTO.getDescription()
        );
    }

    public Person findPersonById(String id) {
        return this.personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found with this id."));
    }

    public Person updatePersonById(String id, PersonDTO personDTO, MultipartFile avatar) {
        LOGGER.info("Updating person.");
        LOGGER.debug("Person id: {}", id);
        LOGGER.debug("PersonDTO: {}", personDTO);

        Person person = this.findPersonById(id);

        if (avatar != null) {
            String avatarFileName = this.imageService.saveImage(avatar, PERSON_PATH_URI.getType());
            String urlAvatar = this.parseUrlAvatarByFileName(avatarFileName);

            person.setAvatar(urlAvatar);
        }

        this.setPersonByDTO(person, personDTO);

        return this.personRepository.save(person);
    }

    private void setPersonByDTO(Person person, PersonDTO personDTO) {
        LOGGER.info("Updating fields");

        person.setDisplayedName(personDTO.getDisplayedName());
        person.setDescription(personDTO.getDescription());
    }

    private String parseUrlAvatarByFileName(String avatarFileName) {
        return this.imageService.getDefaultUrl(avatarFileName, PERSON_PATH_URI.getType());
    }
}
