package com.rubenskj.portfolio.controller;

import com.rubenskj.portfolio.dto.PersonDTO;
import com.rubenskj.portfolio.services.PersonService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public PersonDTO createPerson(@RequestParam("avatarFile") MultipartFile file, @Valid PersonDTO personDTO) {
        return PersonDTO.of(this.personService.save(personDTO, file));
    }

    @GetMapping("/{id}")
    public PersonDTO getPersonById(@PathVariable("id") String id) {
        return PersonDTO.of(this.personService.findPersonById(id));
    }

    @PutMapping("/{id}")
    public PersonDTO updatePersonById(@PathVariable("id") String id, @RequestParam(name = "avatarFile", required = false) MultipartFile avatar, @Valid PersonDTO personDTO) {
        return PersonDTO.of(this.personService.updatePersonById(id, personDTO, avatar));
    }

    @PatchMapping("/{id}")
    public PersonDTO updateAvatarImage(@PathVariable("id") String id, @RequestParam("avatar") MultipartFile avatar) {
        return PersonDTO.of(this.personService.updatePersonAvatarById(id, avatar));
    }
}
