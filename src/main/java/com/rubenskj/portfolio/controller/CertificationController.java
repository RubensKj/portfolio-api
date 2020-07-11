package com.rubenskj.portfolio.controller;

import com.rubenskj.portfolio.dto.CertificationDTO;
import com.rubenskj.portfolio.services.CertificationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/certification")
public class CertificationController {

    private final CertificationService certificationService;

    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @PostMapping("/{personId}")
    public CertificationDTO createCertification(@PathVariable("personId") String personId, @RequestParam("imageFile") MultipartFile imageFile, @Valid CertificationDTO certificationDTO) {
        return CertificationDTO.of(this.certificationService.createCertification(personId, imageFile, certificationDTO));
    }
}
