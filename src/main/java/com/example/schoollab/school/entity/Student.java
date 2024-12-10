package com.example.schoollab.school.entity;

import java.io.Serializable;
import java.util.UUID;

import com.example.schoollab.school.dto.StudentDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "students")
public class Student implements Serializable {
    @Id
    private UUID id;
    private String name;
    private String surname;
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "group")
    private Group group;

    public Student() {}

    public Student(UUID id, String name, String surname, Integer age, Group group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.group = group;
    }

    public boolean equalsByHashCode(Student student) {
        return this.hashCode() == student.hashCode();
    }

    @Override
    public String toString() {
        return "Student(id=" + this.getId() + ", name=" + this.getName() + ", surname=" + this.getSurname() + ", age=" + this.getAge() + ", group=" + this.getGroup() + ")";
    }

    public StudentDto toStudentDto() {
        return StudentDto.builder()
                .id(this.getId())
                .name(this.getName())
                .surname(this.getSurname())
                .age(this.getAge())
                .groupName(this.getGroup().getName())
                .build();
    }


}
