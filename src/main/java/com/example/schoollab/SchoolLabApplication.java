package com.example.schoollab;

import com.example.schoollab.school.entity.Student;
import com.example.schoollab.school.entity.Group;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class SchoolLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolLabApplication.class, args);

        List<Group> groups = new ArrayList<>();

        var group1 = Group.builder()
                .id(UUID.randomUUID())
                .name("group 1")
                .description("group 1 description")
                .students(new ArrayList<>())
                .build();

        var group2 = Group.builder()
                .id(UUID.randomUUID())
                .name("group 2")
                .description("group 2 description")
                .students(new ArrayList<>())
                .build();

        var student1 = Student.builder()
                .id(UUID.randomUUID())
                .name("student 1")
                .surname("student 1 surname")
                .age(20)
                .group(group1)
                .build();

        var student2 = Student.builder()
                .id(UUID.randomUUID())
                .name("student 2")
                .surname("student 2 surname")
                .age(20)
                .group(group1)
                .build();

        var student3 = Student.builder()
                .id(UUID.randomUUID())
                .name("student 3")
                .surname("student 3 surname")
                .age(20)
                .group(group2)
                .build();

        var student4 = Student.builder()
                .id(UUID.randomUUID())
                .name("student 4")
                .surname("student 4 surname")
                .age(20)
                .group(group2)
                .build();

        group1.addStudent(student1);
        group1.addStudent(student2);
        group2.addStudent(student3);
        group2.addStudent(student4);

        groups.add(group1);
        groups.add(group2);


//        TASK2
        groups.forEach(group -> {
            System.out.println(group.toString());
            group.students.forEach(student -> {
                System.out.println(student.toString());
            });
        });
    }

}
