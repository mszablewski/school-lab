package com.example.schoollab.school.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Group implements Serializable {
    public UUID id;
    public String name;
    public String description;
    public List<Student> students;

    public Group() {}

    public Group(UUID id, String name, String description, List<Student> students) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.students = students;
    }

    public boolean equalsByHashCode(Group group) {
        return this.hashCode() == group.hashCode();
    }

    public void addStudent(Student student) {
        student.group = this;
        students.add(student);
    }

    @Override
    public String toString() {
        return "Group (id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ")";
    }

}
