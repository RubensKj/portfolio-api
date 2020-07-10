package com.rubenskj.portfolio.services;

import com.rubenskj.portfolio.model.Certification;
import com.rubenskj.portfolio.repository.ICertificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificationService {

    private final ICertificationRepository certificationRepository;

    public CertificationService(ICertificationRepository certificationRepository) {
        this.certificationRepository = certificationRepository;
    }

    public List<Certification> getAllCertificationFromPerson(String personId) {
        return this.certificationRepository.findAllByPersonId(personId);
    }
}
