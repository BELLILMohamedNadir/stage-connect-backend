package com.example.stageconnect.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String saveFile(MultipartFile file, Long userId) throws IOException;
    Resource loadFileAsResource(String fileName);
    String generateFileUrl(String fileName);
}
