package com.zoya.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Arrays;

@MapperScan(basePackages = "com.zoya.homework.mapper")
@SpringBootApplication
public class HomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
    }

}
