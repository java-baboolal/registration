package com.champion.academy.regististion.entities;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Contact {
    private String contactNumber;
    private String address;
    private String city;
    private String state;
    private Integer pincode;
}
