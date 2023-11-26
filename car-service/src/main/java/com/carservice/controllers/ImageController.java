package com.carservice.controllers;

import com.carservice.core.configuration.CloudinaryUploader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ImageController {

    private final CloudinaryUploader cloudinaryUploader;

    public ImageController(CloudinaryUploader cloudinaryUploader) {
        this.cloudinaryUploader = cloudinaryUploader;
    }

    public String uploadImage(String base64Data) throws IOException {
        return cloudinaryUploader.uploadBase64Image(base64Data);
    }
}
