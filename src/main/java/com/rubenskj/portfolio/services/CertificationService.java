package com.rubenskj.portfolio.services;

import com.rubenskj.portfolio.dto.CertificationDTO;
import com.rubenskj.portfolio.file.service.ImageService;
import com.rubenskj.portfolio.model.Certification;
import com.rubenskj.portfolio.repository.ICertificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CertificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificationService.class);

    private final ICertificationRepository certificationRepository;
    private final ImageService imageService;

    public CertificationService(ICertificationRepository certificationRepository, ImageService imageService) {
        this.certificationRepository = certificationRepository;
        this.imageService = imageService;
    }

    public Certification createCertification(String personId, MultipartFile imageFile, CertificationDTO certificationDTO) {
        LOGGER.info("Creating Certification");
        Certification certification = this.createCertificationFromDTO(certificationDTO);

        if (imageFile != null) {
            LOGGER.info("Saving Image Certification");
            String saveImageFile = this.imageService.saveImage(imageFile);

            String imageUrl = this.imageService.getDefaultUrl(saveImageFile);

            certification.setImage(imageUrl);
        }

        LOGGER.info("Saving Certification");
        return this.certificationRepository.save(certification);
    }

    private Certification createCertificationFromDTO(CertificationDTO certificationDTO) {
        return new Certification(
                certificationDTO.getPersonId(),
                certificationDTO.getImage(),
                certificationDTO.getTitle(),
                certificationDTO.getDescription(),
                certificationDTO.getCertificationUrl()
        );
    }

    public List<Certification> getAllCertificationFromPerson(String personId) {
        return this.certificationRepository.findAllByPersonId(personId);
    }
}
