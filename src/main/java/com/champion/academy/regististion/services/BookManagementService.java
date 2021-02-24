package com.champion.academy.regististion.services;

import com.champion.academy.regististion.dto.AuthorDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface BookManagementService {
    ResponseEntity<String> createAuthor(AuthorDTO authorDTO);

    ResponseEntity<String> createBooks(List<Map<String, Object>> request);

    Map<String, Object> getAutherDetail(Integer id);
}
