package com.bxg.pyg.sellgoods.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.bxg.pyg.mapper"})
public class PygApplication {
    public static void main(String[] args) {
        SpringApplication.run(PygApplication.class);
    }
}
