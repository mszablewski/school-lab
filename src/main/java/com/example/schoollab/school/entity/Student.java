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
    public Group group;

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

}
