package com.instagram_clone.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
public class FileSaveUtil {

    private static final String FILE_PATH = "/Users/leemac/IdeaProjects/";

    public static String uploadImage(MultipartFile file) {
        if(Objects.isNull(file) || file.isEmpty()) {
            return "";
        }
        String saveFileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUIDGenerator.generateUUID()+saveFileType;
        try {
            file.transferTo(new File(FILE_PATH+fileName));
        } catch (IOException e) {
            log.error("파일 저장 실패", e);
        }
        return fileName;
    }

}
