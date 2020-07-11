package com.rubenskj.portfolio.services;

import com.rubenskj.portfolio.dto.CertificationDTO;
import com.rubenskj.portfolio.model.Certification;
import com.rubenskj.portfolio.repository.ICertificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CertificationService {

    private final ICertificationRepository certificationRepository;

    public CertificationService(ICertificationRepository certificationRepository) {
        this.certificationRepository = certificationRepository;
    }

    public Certification createCertification(String personId, MultipartFile imageFile, CertificationDTO certificationDTO) {
        return null;
    }

    public List<Certification> getAllCertificationFromPerson(String personId) {
        return this.certificationRepository.findAllByPersonId(personId);
    }
}
