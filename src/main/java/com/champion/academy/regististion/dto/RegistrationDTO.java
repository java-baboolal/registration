package com.champion.academy.regististion.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationDTO {
    private Long id;

    @NotNull(message = "username can not be blank!")
    private String userName;
    @Size(min = 1, max = 10, message = "Name should not be more than 10 characters!")
    private String firstName;
    private String lastName;
    private String email;
    private ContactDTO contactDTO;
}
