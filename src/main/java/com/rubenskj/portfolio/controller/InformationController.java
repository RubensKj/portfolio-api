package com.rubenskj.portfolio.controller;

import com.rubenskj.portfolio.dto.InformationDTO;
import com.rubenskj.portfolio.services.InformationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/information")
public class InformationController {

    private final InformationService informationService;

    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/edit/{personId}")
    public InformationDTO getInformationDTOWithoutPinnedSort(@PathVariable("personId") String personId) {
        return this.informationService.getInformationDTOByPersonId(personId);
    }

    @GetMapping("/{personId}")
    public InformationDTO getInformationDTO(@PathVariable("personId") String personId) {
        return this.informationService.getInformationDTOByPersonIdWithPinnedSorted(personId);
    }
}
