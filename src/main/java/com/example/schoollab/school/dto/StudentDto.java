package com.example.schoollab.school.dto;

import com.example.schoollab.school.entity.Student;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class StudentDto {
    public UUID id;
    public String name;
    public String surname;
    public Integer age;
    public String groupName;

    public StudentDto() {}

    public StudentDto(UUID id, String name, String surname, Integer age, String groupName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.groupName = groupName;
    }
}
