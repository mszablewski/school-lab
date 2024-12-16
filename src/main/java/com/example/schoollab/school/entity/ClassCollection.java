package com.example.schoollab.school.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "class_collections")
public class ClassCollection implements Serializable {
    @Id
    private UUID id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "classCollection", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Student> students;

    public ClassCollection() {}

    public ClassCollection(UUID id, String name, String description, List<Student> students) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.students = students;
    }

    public boolean equalsByHashCode(ClassCollection classCollection) {
        return this.hashCode() == classCollection.hashCode();
    }

    public void addStudent(Student student) {
        student.setClassCollection(this);
        students.add(student);
    }

    @Override
    public String toString() {
        return "ClassCollection (id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ")";
    }

}
