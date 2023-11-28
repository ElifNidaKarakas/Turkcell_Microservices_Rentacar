package com.carservice.core.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Map;

@Configuration
public class CloudinaryUploader {

    private final Cloudinary cloudinary;
    private final String cloudName = "dmrmgvwbs";
    private final String apiKey = "465592552891791";
    private final String apiSecret = "MTf0rtJfAnA-28zN9687xNsLFBY";

    public CloudinaryUploader() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }

    public String uploadBase64Image(String base64Data) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(base64Data, ObjectUtils.emptyMap());
        return (String) uploadResult.get("url");
    }
}