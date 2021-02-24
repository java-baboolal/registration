package com.champion.academy.regististion.dto;

import lombok.Data;

@Data
public class ContactDTO {
    private String contactNumber;
    private String address;
    private String city;
    private String state;
    private Integer pincode;
}
