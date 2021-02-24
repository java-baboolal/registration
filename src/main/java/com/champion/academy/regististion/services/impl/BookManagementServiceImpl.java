package com.champion.academy.regististion.services.impl;

import com.champion.academy.regististion.dto.AuthorDTO;
import com.champion.academy.regististion.entities.Auther;
import com.champion.academy.regististion.entities.Book;
import com.champion.academy.regististion.repositories.AutherRepository;
import com.champion.academy.regististion.repositories.BookRepository;
import com.champion.academy.regististion.services.BookManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookManagementServiceImpl implements BookManagementService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AutherRepository autherRepository;

    @Override
    public ResponseEntity<String> createAuthor(AuthorDTO authorDTO) {
        Auther auther = new Auther();
        auther.setName(authorDTO.getName());
        auther.setAge(authorDTO.getAge());
        autherRepository.save(auther);
        return ResponseEntity.ok("Author save successfully!");
    }

    @Override
    @Transactional
    public ResponseEntity<String> createBooks(List<Map<String, Object>> request) {
        List<Book> books = new ArrayList<>();
        List<Integer> autherIds = request.stream().map(
                req -> (Integer) req.get("autherId")
        ).distinct().collect(Collectors.toList());
        List<Auther> authers = autherRepository.findByIdIn(autherIds);
        if (autherIds.size() != authers.size())
            throw new RuntimeException("Mis match in Auther ids");
        Map<Integer, Auther> autherMap = authers.stream()
                .collect(Collectors.toMap(Auther::getId, v -> v));
        for (Map bookMap : request) {
            Book book = new Book();
            book.setCode(bookMap.get("code").toString());
            book.setName(bookMap.get("name").toString());
            //book.setPublishDate((new Date()).getTime());
            Integer autherId = (Integer) bookMap.get("autherId");
            Auther auther = autherMap.get(autherId);
            book.setAuther(auther);
            books.add(book);
        }
        bookRepository.save(books);
        return ResponseEntity.ok("Books save successfully!");
    }

    @Override
    public Map<String, Object> getAutherDetail(Integer id) {
        Auther auther = autherRepository.findOne(id);
        //List<Book> books = bookRepository.findByAuther(auther);
        // Now we are using bi-directional mapping and lazy loading.
        List<Book> books = auther.getBooks();
        Map<String, Object> respMap = new HashMap<>();
        respMap.put("AuthorName", auther.getName());
        List<Map<String, String>> bookMapList = new ArrayList<>();
        for (Book book : books) {
            Map<String, String> bMap = new HashMap<>();
            bMap.put("bookName", book.getName());
            bMap.put("bookCode", book.getCode());
            bMap.put("bookPublishDate", book.getPublishDate().toString());
            bookMapList.add(bMap);
        }
        respMap.put("booksDetail", bookMapList);
        return respMap;
    }
}
