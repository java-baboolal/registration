package com.champion.academy.regististion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@PropertySource("classpath:custom_messages.properties")
@EnableTransactionManagement
public class RegististionApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegististionApiApplication.class, args);
/*
        File myObj = new File("filename.txt");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
        }*/
    }

}
