package com.keke;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages ="com.keke.mapper")
public class SimpleMallApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleMallApplication.class,args);
    }
}
