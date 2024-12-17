package com.example.schoollab.school;

import com.example.schoollab.school.entity.ClassCollection;
import com.example.schoollab.school.entity.Student;
import com.example.schoollab.school.service.ClassCollectionService;
import com.example.schoollab.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

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
                    addStudent(scanner);
                    break;
                case "4":
                    deleteStudent(scanner);
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

    private void addStudent(Scanner scanner) {
        try {
            System.out.print("Enter student's name: ");
            String name = scanner.nextLine();

            System.out.print("Enter student's surname: ");
            String surname = scanner.nextLine();

            System.out.print("Enter student's age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.println("Available class collections:");
            classCollectionService.getAllClassCollections().forEach(System.out::println);
            System.out.print("Enter class collection ID for the student: ");
            String classCollectionId = scanner.nextLine();

            UUID collectionId = UUID.fromString(classCollectionId);
            ClassCollection classCollection = classCollectionService.getClassCollectionById(collectionId);

            if (classCollection == null) {
                System.out.println("Class collection not found.");
                return;
            }

            Student student = Student.builder()
                    .id(UUID.randomUUID())
                    .name(name)
                    .surname(surname)
                    .age(age)
                    .classCollection(classCollection)
                    .build();

            studentService.createStudent(student);
            System.out.println("Student added successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred while adding the student: " + e.getMessage());
        }
    }

    private void deleteStudent(Scanner scanner) {
        try {
            System.out.print("Enter the ID of the student to delete: ");
            String studentId = scanner.nextLine();
            UUID id = UUID.fromString(studentId);
            studentService.deleteStudent(id);

        } catch (Exception e) {
            System.out.println("An error occurred while deleting the student: " + e.getMessage());
        }
    }

}