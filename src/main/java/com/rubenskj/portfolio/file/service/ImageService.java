package com.rubenskj.portfolio.file.service;

import com.rubenskj.portfolio.enums.PathTypeEnum;
import com.rubenskj.portfolio.exception.NotFoundException;
import com.rubenskj.portfolio.file.property.ImageProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);

    private final Path path;
    private final HttpServletRequest request;

    public ImageService(ImageProperty imageProperty, HttpServletRequest request) {
        this.path = Paths.get(imageProperty.getPath())
                .toAbsolutePath().normalize();
        this.request = request;

        try {
            Files.createDirectories(this.path);

            new ArrayList<>(Arrays.asList(PathTypeEnum.values())).forEach(pathType -> {
                try {
                    Files.createDirectories(this.path.resolve(pathType.getType()));
                } catch (IOException e) {
                    LOGGER.error("It wasn't able to create folder from path type. PathTypeEnum: {}", pathType.getType());
                    LOGGER.error("Exception: e -> ", e);
                }
            });

        } catch (Exception ex) {
            throw new IllegalArgumentException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public Path getPath() {
        return path;
    }

    public List<String> saveImages(List<MultipartFile> images, String typeToPath) {
        List<String> imagesUrl = new ArrayList<>();

        images.forEach(imageFile -> {
            String fileName = this.saveImage(imageFile, typeToPath);

            imagesUrl.add(fileName);
        });

        return imagesUrl;
    }

    public Resource loadFileAsResource(String fileName, String typePath) {
        try {
            final String fileMinResolve = typePath + "/" + fileName;

            Path filePath = this.path.resolve(fileMinResolve).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new NotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new NotFoundException("File not found " + fileName, ex);
        }
    }

    public String saveImage(MultipartFile avatar, String typeToPath) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(avatar.getOriginalFilename()));

        try {
            if (fileName.contains("..")) {
                throw new IllegalArgumentException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation;
            if (StringUtils.isEmpty(typeToPath)) {
                targetLocation = this.path.resolve(fileName);
            } else {
                targetLocation = this.path.resolve(typeToPath + "/" + fileName);
            }

            Files.copy(avatar.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new IllegalArgumentException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public String getDefaultUrl(String avatarFileName, String typeToPath) {
        Resource resource = this.loadFileAsResource(avatarFileName, typeToPath);

        URI uri = URI.create(request.getRequestURL().toString()).resolve(request.getContextPath());

        return uri.getScheme().concat("://").concat(uri.getAuthority().concat("/api/images/" + typeToPath + "/").concat(resource.getFilename()));
    }
}
