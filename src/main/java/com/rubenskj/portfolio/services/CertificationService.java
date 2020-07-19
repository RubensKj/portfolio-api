package com.rubenskj.portfolio.services;

import com.rubenskj.portfolio.dto.CertificationDTO;
import com.rubenskj.portfolio.exception.NotFoundException;
import com.rubenskj.portfolio.file.service.ImageService;
import com.rubenskj.portfolio.model.Certification;
import com.rubenskj.portfolio.repository.ICertificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

import static com.rubenskj.portfolio.PathTypeEnum.CERTIFICATION_PATH_URI;

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
        Certification certification = this.createCertificationFromDTO(personId, certificationDTO);

        this.handleImage(imageFile, certification);

        LOGGER.info("Saving Certification");
        return this.certificationRepository.save(certification);
    }

    private Certification createCertificationFromDTO(String personId, CertificationDTO certificationDTO) {
        return new Certification(
                personId,
                certificationDTO.getImage(),
                certificationDTO.getTitle(),
                certificationDTO.getDescription(),
                certificationDTO.getCertificationUrl()
        );
    }

    public Certification findById(String certificationId) {
        return this.certificationRepository.findById(certificationId).orElseThrow(() -> new NotFoundException("Certification not found with this id."));
    }

    public List<Certification> getAllCertificationFromPerson(String personId) {
        return this.certificationRepository.findAllByPersonId(personId);
    }

    public Certification updateById(String certificationId, MultipartFile imageFile, CertificationDTO certificationDTO) {
        Certification certification = this.findById(certificationId);

        this.handleImage(imageFile, certification);

        this.setCertificationFromDTO(certification, certificationDTO);

        return this.certificationRepository.save(certification);
    }

    private void setCertificationFromDTO(Certification certification, CertificationDTO certificationDTO) {
        certification.setTitle(certificationDTO.getTitle());
        certification.setDescription(certificationDTO.getDescription());
        certification.setCertificationUrl(certificationDTO.getCertificationUrl());
        certification.setPinned(certificationDTO.isPinned());

        certification.setUpdatedAt(LocalDateTime.now());
    }

    private void handleImage(MultipartFile imageFile, Certification certification) {
        if (imageFile == null) {
            return;
        }

        String placeCertifications = CERTIFICATION_PATH_URI.getType();

        LOGGER.info("Saving Image Certification");
        String saveImageFile = this.imageService.saveImage(imageFile, placeCertifications);

        String imageUrl = this.imageService.getDefaultUrl(saveImageFile, placeCertifications);

        certification.setImage(imageUrl);
    }

    public void deleteById(String certificationId) {
        LOGGER.info("Deleting certification by id. Id: {}", certificationId);
        this.certificationRepository.deleteById(certificationId);
    }
}
