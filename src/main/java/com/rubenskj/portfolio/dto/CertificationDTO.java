package com.rubenskj.portfolio.dto;

import com.rubenskj.portfolio.model.Certification;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CertificationDTO {

    private String id;

    private String image;

    @NotEmpty(message = "Title cannot be empty")
    @NotNull(message = "Title cannot be null")
    private String title;
    private String description;

    @NotEmpty(message = "Certification Url cannot be empty")
    @NotNull(message = "Certification Url cannot be null")
    private String certificationUrl;

    private Boolean pinned;

    private LocalDateTime updatedAt;
    private LocalDateTime creationAt = LocalDateTime.now();

    public CertificationDTO() {
    }

    public CertificationDTO(String id, String image, String title, String description, String certificationUrl, Boolean pinned, LocalDateTime updatedAt, LocalDateTime creationAt) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.certificationUrl = certificationUrl;
        this.pinned = pinned;
        this.updatedAt = updatedAt;
        this.creationAt = creationAt;
    }

    public static CertificationDTO of(Certification certification) {
        return new CertificationDTO(
                certification.getId(),
                certification.getImage(),
                certification.getTitle(),
                certification.getDescription(),
                certification.getCertificationUrl(),
                certification.isPinned(),
                certification.getUpdatedAt(),
                certification.getCreationAt()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCertificationUrl() {
        return certificationUrl;
    }

    public void setCertificationUrl(String certificationUrl) {
        this.certificationUrl = certificationUrl;
    }

    public Boolean getPinned() {
        return pinned;
    }

    public void setPinned(Boolean pinned) {
        this.pinned = pinned;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreationAt() {
        return creationAt;
    }

    public void setCreationAt(LocalDateTime creationAt) {
        this.creationAt = creationAt;
    }
}
