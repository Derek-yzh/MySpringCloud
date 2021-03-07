package com.example.nacosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class NacosPApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosPApplication.class, args);
    }

    @RequestMapping
    public String test(){
        return "nacos-p";
    }

}
