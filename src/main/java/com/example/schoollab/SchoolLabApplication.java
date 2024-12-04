package com.example.schoollab;

import com.example.schoollab.school.entity.Student;
import com.example.schoollab.school.entity.Group;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ForkJoinPool;

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

//TASK2
        System.out.println("TASK2");
        groups.forEach(group -> {
            System.out.println(group.toString());
            group.getStudents().forEach(student -> {
                System.out.println(student.toString());
            });
        });

//TASK3
        System.out.println("TASK3");
        var allStudents = groups.stream().flatMap(group -> group.getStudents().stream()).toList();
        allStudents.forEach(student -> {
            System.out.println(student.toString());
        });

//TASK4
        System.out.println("TASK4");
        var allStudentsFilteredByNameSortedBySurname = allStudents.stream().filter(student -> student.getName().equals("student 1")).sorted(Comparator.comparing(Student::getSurname)).toList();
        allStudentsFilteredByNameSortedBySurname.forEach(student -> {
            System.out.println(student.toString());
        });

//TASK5
        System.out.println("TASK5");
        var studentDtoList = allStudents.stream().map(Student::toStudentDto).sorted(Comparator.naturalOrder()).toList();

        studentDtoList.forEach(student -> {
            System.out.println(student.toString());
        });

//TASK6
        System.out.println("TASK6");

        String fileName = "people.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(groups);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Group> readGroups = (List<Group>) ois.readObject();
            readGroups.stream().forEach(
                    group -> {
                        System.out.println(group.toString());
                        group.getStudents().stream().forEach(student -> {
                            System.out.println(student.toString());
                        });
                    }
            );
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("GROUPS");
        System.out.println(groups.size());

//TASK7
        System.out.println("TASK7");

        ForkJoinPool threadPool = new ForkJoinPool(1);
        try {
            threadPool.submit(() -> {
                groups.parallelStream().forEach(group -> {
                    group.getStudents().forEach(student -> {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                        System.out.println(student);
                    });
                });
            }).get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
