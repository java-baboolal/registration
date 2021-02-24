package com.champion.academy.regististion.repositories;

import com.champion.academy.regististion.entities.Auther;
import com.champion.academy.regististion.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuther(Auther auther);
}
