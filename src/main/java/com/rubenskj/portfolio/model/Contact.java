package com.rubenskj.portfolio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Contact {

    @Id
    private String id;
    private String completeName;
    private String email;
    private String description;
    private String status;

    public Contact() {
    }

    public Contact(String completeName, String email, String description, String status) {
        this.completeName = completeName;
        this.email = email;
        this.description = description;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
