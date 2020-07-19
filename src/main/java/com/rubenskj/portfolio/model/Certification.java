package com.rubenskj.portfolio.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Certification {

    @Id
    private String id;
    private String personId;
    private String image;
    private String title;
    private String description;
    private String certificationUrl;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @CreationTimestamp
    private LocalDateTime creationAt = LocalDateTime.now();

    private boolean isPinned;

    public Certification(String personId, String image, String title, String description, String certificationUrl, boolean isPinned) {
        this.personId = personId;
        this.image = image;
        this.title = title;
        this.description = description;
        this.certificationUrl = certificationUrl;
        this.updatedAt = LocalDateTime.now();
        this.isPinned = isPinned;
    }

    public String getId() {
        return id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreationAt() {
        return creationAt;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }
}
