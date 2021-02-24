package com.champion.academy.regististion.controller;


import com.champion.academy.regististion.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class WebController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/api/upload/file")
    public String uploadFile(@RequestPart MultipartFile inputFile) {
        return fileUploadService.uploadFile(inputFile);
    }
}
