package com.example.schoollab.group.entity;

import com.example.schoollab.student.entity.Student;
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
@Table(name = "groups")
public class Group implements Serializable {
    @Id
    private UUID id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Student> students;

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
        student.setGroup(this);
        students.add(student);
    }

    @Override
    public String toString() {
        return "Group (id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ")";
    }

}
