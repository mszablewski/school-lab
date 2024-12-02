package com.example.schoollab.school.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
@Builder
public class Group {
    public UUID id;
    public String name;
    public String description;
    public Collection<Student> students;

    public Group() {}

    public Group(UUID id, String name, String description, Collection<Student> students) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.students = students;
    }
}
