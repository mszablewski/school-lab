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
    @JoinColumn(name = "class_collection")
    private ClassCollection classCollection;

    public Student() {}

    public Student(UUID id, String name, String surname, Integer age, ClassCollection classCollection) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.classCollection = classCollection;
    }

    public boolean equalsByHashCode(Student student) {
        return this.hashCode() == student.hashCode();
    }

    @Override
    public String toString() {
        return "Student(id=" + this.getId() + ", name=" + this.getName() + ", surname=" + this.getSurname() + ", age=" + this.getAge() + ", classCollection=" + this.getClassCollection() + ")";
    }

    public StudentDto toStudentDto() {
        return StudentDto.builder()
                .id(this.getId())
                .name(this.getName())
                .surname(this.getSurname())
                .age(this.getAge())
                .classCollectionName(this.getClassCollection().getName())
                .build();
    }


}
