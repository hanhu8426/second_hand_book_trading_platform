package com.secondhandbookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SecondHandBookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondHandBookStoreApplication.class, args);
    }

}
