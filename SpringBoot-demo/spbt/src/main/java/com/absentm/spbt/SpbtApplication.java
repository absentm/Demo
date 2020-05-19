package com.absentm.spbt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.absentm.spbt.dao")
public class SpbtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpbtApplication.class, args);
    }

}
