package com.example.schoollab.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.schoollab")
@EnableJpaRepositories(basePackages = "com.example.schoollab.school.repository")
public class SchoolLabApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchoolLabApplication.class, args);
    }
}
