package com.example.schoollab.school;

import com.example.schoollab.school.service.ClassCollectionService;
import com.example.schoollab.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@DependsOn("initializeData")
public class CommandLineApp implements CommandLineRunner {
    private final StudentService studentService;
    private final ClassCollectionService classCollectionService;

    @Autowired
    public CommandLineApp(StudentService studentService, ClassCollectionService classCollectionService) {
        this.studentService = studentService;
        this.classCollectionService = classCollectionService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nAvailable commands:");
            System.out.println("1. List all class collections");
            System.out.println("2. List all students");
            System.out.println("3. Add a new student");
            System.out.println("4. Delete a student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    listClassCollections();
                    break;
                case "2":
                    listStudents();
                    break;
                case "3":
                    addStudent();
                    break;
                case "4":
                    deleteStudent();
                    break;
                case "5":
                    running = false;
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
        scanner.close();
    }

    private void listClassCollections() {
        classCollectionService.getAllClassCollections()
                .forEach(System.out::println);
    }

    private void listStudents() {
        studentService.getAllStudents().forEach(System.out::println);
    }

    private void addStudent() {

    }

    private void deleteStudent() {

    }

}