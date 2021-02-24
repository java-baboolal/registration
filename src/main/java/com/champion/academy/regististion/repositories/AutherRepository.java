package com.champion.academy.regististion.repositories;

import com.champion.academy.regististion.entities.Auther;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutherRepository extends JpaRepository<Auther, Integer> {

    List<Auther> findByIdIn(List<Integer> autherIds);
}
