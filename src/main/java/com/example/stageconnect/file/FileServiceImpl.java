package com.example.stageconnect.file;

import com.example.stageconnect.user.model.BaseUser;
import com.example.stageconnect.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Value("${app.base-url}")
    private String baseUrl;

    private Path fileStorageLocation;

    private final FileRepository fileRepository;

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create upload directory", ex);
        }
    }

    @Override
    public String saveFile(MultipartFile file, Long userId) {
        // Validate file
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        // Validate file type (images and PDFs allowed)
        String contentType = file.getContentType();
        if (contentType == null || (!contentType.startsWith("image/") && !contentType.equals("application/pdf"))) {
            throw new IllegalArgumentException("Only image and PDF files are allowed");
        }

        // Generate unique filename
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName = UUID.randomUUID() + fileExtension;

        try {
            // Check for path traversal
            Path targetLocation = this.fileStorageLocation.resolve(fileName).normalize();
            if (!targetLocation.getParent().equals(this.fileStorageLocation)) {
                throw new RuntimeException("Cannot store file outside current directory");
            }

            // Copy file to target location
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            //save the file
            BaseUser user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));

            //set all pdfs or images currently to false
            List<String> imageExtensions = Arrays.asList(".jpg", ".jpeg", ".png");
            if (fileName.endsWith(".pdf")) {
                // Set all PDF files to 'false'
                List<File> pdfFiles = fileRepository.findAllByUserAndPathEndingWith(user, ".pdf");
                for (File pdf : pdfFiles) {
                    pdf.setCurrent(false);
                }
                fileRepository.saveAll(pdfFiles);  // Save all updated PDF files
            }
            else if (imageExtensions.stream().anyMatch(fileName::endsWith)) {
                // Set all images files to 'false'
                for (String extension : imageExtensions) {
                    List<File> imageFiles = fileRepository.findAllByUserAndPathEndingWith(user, extension);
                    for (File image : imageFiles) {
                        image.setCurrent(false);
                    }
                    fileRepository.saveAll(imageFiles);
                }
            }

            fileRepository.save(
                    File.builder()
                            .path(fileName)
                            .user(user)
                            .current(true)
                            .build()
            );

            // Return just the filename, not the full path
            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to store file " + fileName, ex);
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found " + fileName, ex);
        }
    }

    @Override
    public String generateFileUrl(String fileName) {
        if (fileName == null) {
            return null;
        }
        // Return an HTTP URL, not a file path
        return baseUrl + "/api/files/" + fileName;
    }
}
