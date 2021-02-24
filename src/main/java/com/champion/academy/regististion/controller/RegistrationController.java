package com.champion.academy.regististion.controller;


import com.champion.academy.regististion.dto.RegistrationDTO;
import com.champion.academy.regististion.exceptions.UserNotFoundException;
import com.champion.academy.regististion.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private ConfigurableEnvironment env;

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return "Welcome on registration module";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegistrationDTO registration) {
        return registrationService.registerUser(registration);
    }

    @GetMapping("/findUser/{id}")
    public RegistrationDTO findByUserName(@PathVariable Long id,
                                          @RequestParam(value = "email", required = false) String email,
                                          @RequestParam(value = "param2", required = false) String param2) {

        RegistrationDTO registrationDTO = null;
        try {
            registrationDTO = registrationService.findByUserName(id);

        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException(env.getProperty("user.not.found"));
        } catch (Exception e) {
            throw e;
        }
        return registrationDTO;
    }

    @GetMapping("/findUser")
    public RegistrationDTO findByUser(@RequestParam(value = "id", required = false) Long id,
                                      @RequestParam(value = "email", required = false) String email,
                                      @RequestParam(value = "param2", required = false) String param2) {

        RegistrationDTO registrationDTO = null;
        try {
            registrationDTO = registrationService.findByUserName(id, email);

        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException(env.getProperty("user.not.found"));
        } catch (Exception e) {
            throw e;
        }
        return registrationDTO;
    }

    @GetMapping("/findAllUsers")
    public List<RegistrationDTO> findAllUsers() {
        return registrationService.findAllUsers();
    }

    @GetMapping("/findAllUsers/{pageNumber}/{size}")
    public List<RegistrationDTO> findAllUsersPageWise(@PathVariable Integer pageNumber,
                                                      @PathVariable Integer size) {
        return registrationService.findAllUsersPageWise(pageNumber, size);
    }
}
