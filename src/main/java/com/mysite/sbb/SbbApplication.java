package com.mysite.sbb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class SbbApplication {
    @GetMapping("/question /list")
    public static void main(String[] args) {
        SpringApplication.run(SbbApplication.class, args);
    }

}
