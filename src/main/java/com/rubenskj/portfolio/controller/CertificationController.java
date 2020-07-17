package com.rubenskj.portfolio.controller;

import com.rubenskj.portfolio.dto.CertificationDTO;
import com.rubenskj.portfolio.services.CertificationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

import static com.rubenskj.portfolio.util.ParserUtil.parseCertificationToDTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    private final CertificationService certificationService;

    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @PostMapping("/{personId}")
    public CertificationDTO createCertification(@PathVariable("personId") String personId, @RequestParam("imageFile") MultipartFile imageFile, @Valid CertificationDTO certificationDTO) {
        return CertificationDTO.of(this.certificationService.createCertification(personId, imageFile, certificationDTO));
    }

    @GetMapping("/certification/{certificationId}")
    public CertificationDTO getCertificationById(@PathVariable("certificationId") String certificationId) {
        return CertificationDTO.of(this.certificationService.findById(certificationId));
    }

    @GetMapping("/{personId}")
    public List<CertificationDTO> getAllCertification(@PathVariable("personId") String personId) {
        return parseCertificationToDTO(this.certificationService.getAllCertificationFromPerson(personId));
    }

    @PutMapping("/{certificationId}")
    public CertificationDTO updateById(@PathVariable("certificationId") String certificationId, @RequestParam(name = "image", required = false) MultipartFile imageFile, @Valid CertificationDTO certificationDTO) {
        return CertificationDTO.of(this.certificationService.updateById(certificationId, imageFile, certificationDTO));
    }

    @DeleteMapping("/{certificationId}")
    public void deleteById(@PathVariable("certificationId") String certificationId) {
        this.certificationService.deleteById(certificationId);
    }
}
