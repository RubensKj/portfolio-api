package com.rubenskj.portfolio.file.service;

import com.rubenskj.portfolio.file.property.ImageProperty;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    private final Path path;

    public ImageService(ImageProperty imageProperty) {
        this.path = Paths.get(imageProperty.getPath())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.path);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
}
