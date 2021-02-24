package com.champion.academy.regististion.services.impl;

import com.champion.academy.regististion.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadServiceImp implements FileUploadService {

    @Autowired
    ConfigurableEnvironment env;

    @Override
    public String uploadFile(MultipartFile inputFile) {

        String filePath = env.getProperty("file.upload.dir");
        File file = new File(filePath);
        if (file.exists()) {
            file.getParentFile().mkdir();
            file.mkdir();
        }
        String filename = inputFile.getOriginalFilename();
        Path path = Paths.get(filePath);
        String completeFilePaht = filePath + "" + filename;
        try {
            Files.copy(inputFile.getInputStream(), path.resolve(completeFilePaht));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return completeFilePaht;
    }
}
