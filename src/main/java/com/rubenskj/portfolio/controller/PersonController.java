package com.rubenskj.portfolio.controller;

import com.rubenskj.portfolio.dto.PersonDTO;
import com.rubenskj.portfolio.services.PersonService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('RL_ADMIN')")
    public PersonDTO createPerson(@RequestParam("avatarFile") MultipartFile file, @Valid PersonDTO personDTO) {
        return PersonDTO.of(this.personService.save(personDTO, file));
    }

    @GetMapping("/{id}")
    public PersonDTO getPersonById(@PathVariable("id") String id) {
        return PersonDTO.of(this.personService.findPersonById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('RL_ADMIN')")
    public PersonDTO updatePersonById(@PathVariable("id") String id, @RequestParam(name = "avatar", required = false) MultipartFile avatar, @Valid PersonDTO personDTO) {
        return PersonDTO.of(this.personService.updatePersonById(id, personDTO, avatar));
    }
}
