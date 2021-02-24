package com.champion.academy.regististion.repositories;

import com.champion.academy.regististion.entities.RegistrationEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Long> {
    RegistrationEntity findByIdAndEmail(Long id, String email);

    RegistrationEntity findByEmail(String email);

    List<RegistrationEntity> findByContactCityOrderByFirstNameDesc(String city);

    List<RegistrationEntity> findTop10ByContactCityOrderByFirstNameDesc(String city);

    List<RegistrationEntity> findByContactCityOrderByFirstNameDesc(String noida, Pageable pageRequest);
}
