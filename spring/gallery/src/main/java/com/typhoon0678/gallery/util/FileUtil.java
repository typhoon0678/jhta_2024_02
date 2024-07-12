package com.typhoon0678.gallery.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class FileUtil {

    public String[] getImageName(MultipartFile file) {
        String originalTitle = file.getOriginalFilename();

        String fileExtension = originalTitle.substring(originalTitle.lastIndexOf("."));

        String renameTitle = LocalDateTime.now()
                .format(DateTimeFormatter
                        .ofPattern("yyyyMMdd_hhmmss")) + fileExtension;

        return new String[]{originalTitle, renameTitle};
    }

    public boolean saveImage(MultipartFile file,
                             String uploadPath,
                             String fileName) {
        log.info("uploadPath = {}", uploadPath);
        try {
            Path path = Files.write(Paths.get(uploadPath + fileName), file.getBytes());
            log.info("path: " + path);
        } catch (IOException e) {
            log.warn(e.getMessage());
            return false;
        }

        return true;
    }
}
