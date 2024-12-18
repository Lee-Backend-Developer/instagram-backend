package com.instagram_clone.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class FileSaveUtil {
    //파일 저장 위치
    private static final String FILE_PATH = "/Users/leemac/IdeaProjects/";

    public void uploadImage(MultipartFile file) throws IOException {
        String saveFileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUIDGenerator.generateUUID();

        file.transferTo(new File(FILE_PATH+fileName+saveFileType));
    }

}
