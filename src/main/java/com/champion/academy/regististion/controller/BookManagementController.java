package com.champion.academy.regististion.controller;

import com.champion.academy.regististion.dto.AuthorDTO;
import com.champion.academy.regististion.services.BookManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BookManagementController {

    @Autowired
    BookManagementService bookManagementService;

    @PostMapping("/create/auther")
    public ResponseEntity<String> createAuthor(@RequestBody AuthorDTO authorDTO) {
        return bookManagementService.createAuthor(authorDTO);
    }

    @PostMapping("/create/books")
    public ResponseEntity<String> createBooks(@RequestBody List<Map<String, Object>> request) {
        return bookManagementService.createBooks(request);
    }

    @GetMapping("/get/authorDetail/{id}")
    Map<String, Object> getAutherDetail(@PathVariable Integer id) {
        return bookManagementService.getAutherDetail(id);
    }
}
