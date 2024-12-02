package com.example.schoollab;

import com.example.schoollab.school.entity.Student;
import com.example.schoollab.school.entity.Group;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolLabApplication.class, args);

        var studentTest = Student.builder()
                .name("John")
                .surname("Doe")
                .age(12)
                .build();
        System.out.println(studentTest.toString());

        var studentTest2 = Student.builder()
                .name("John")
                .surname("Doe")
                .age(12)
                .build();
        System.out.println(studentTest.toString());

        var groupTest = Group.builder()
                .name("ladybugs")
                .description("group test description")
                .build();

        var groupTest2 = Group.builder()
                .name("ladybugs2")
                .description("group test description")
                .build();

        System.out.println(groupTest.toString());

        System.out.println(studentTest.hashCode());
        System.out.println(studentTest2.hashCode());
        System.out.println(groupTest.hashCode());

        System.out.println(studentTest.equals(studentTest2));
        System.out.println(groupTest.equals(groupTest2));
    }

}
