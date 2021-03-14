package com.rubenskj.portfolio.controller;

import com.rubenskj.portfolio.file.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/images/{typePath}/{filename:.+}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable("typePath") String typePath, @PathVariable("filename") String filename) throws IOException {
        Resource file = imageService.loadFileAsResource(filename, typePath);

        return ResponseEntity.status(HttpStatus.OK).body(file);
    }
}
