package com.bxg.pyg.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class PygWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(PygWebApplication.class);
    }
}
