package com.rubenskj.portfolio.enums;

public enum PathTypeEnum {
    PERSON_PATH_URI("person"),
    CERTIFICATION_PATH_URI("certifications"),
    PROJECT_PATH_URI("projects"),
    CONTACT_PATH_URI("contacts");

    private String type;

    PathTypeEnum(String pathType) {
        this.type = pathType;
    }

    public String getType() {
        return type;
    }
}
