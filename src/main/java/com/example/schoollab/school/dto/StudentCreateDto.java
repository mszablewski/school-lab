package com.example.schoollab.school.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class StudentCreateDto {
    private String name;
    private String surname;
    private Integer age;
    private UUID classCollectionId;
}