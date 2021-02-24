package com.champion.academy.regististion.services;

import com.champion.academy.regististion.dto.RegistrationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegistrationService {

    ResponseEntity<String> registerUser(RegistrationDTO registration);

    RegistrationDTO findByUserName(Long id);

    RegistrationDTO findByUserName(Long id, String email);

    List<RegistrationDTO> findAllUsers();

    List<RegistrationDTO> findAllUsersPageWise(Integer pageNumber, Integer size);
}
