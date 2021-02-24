package com.champion.academy.regististion.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    String uploadFile(MultipartFile inputFile);
}
