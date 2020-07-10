package com.rubenskj.portfolio.controller;

import com.rubenskj.portfolio.dto.InformationDTO;
import com.rubenskj.portfolio.services.InformationService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/information")
public class InformationController {

    private final InformationService informationService;

    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/{personId}")
    public InformationDTO getInformationDTO(@PathVariable("personId") String personId) {
        return this.informationService.getInformationDTOByPersonId(personId);
    }
}
