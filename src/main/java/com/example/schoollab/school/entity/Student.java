package com.example.schoollab.school.entity;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
    public UUID id;
    public String name;
    public String surname;
    public Integer age;

    public Student() {}

    public Student(UUID id, String name, String surname, Integer age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
}
