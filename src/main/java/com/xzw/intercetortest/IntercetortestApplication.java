package com.xzw.intercetortest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@MapperScan("com.xzw.intercetortest.mapper")
public class IntercetortestApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntercetortestApplication.class, args);
    }

}
