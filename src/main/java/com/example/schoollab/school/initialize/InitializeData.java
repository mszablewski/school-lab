package com.example.schoollab.school.initialize;

import com.example.schoollab.school.entity.ClassCollection;
import com.example.schoollab.school.entity.Student;
import com.example.schoollab.school.service.ClassCollectionService;
import com.example.schoollab.school.service.StudentService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class InitializeData implements InitializingBean {

    private final ClassCollectionService classCollectionService;
    private final StudentService studentService;

    @Autowired
    public InitializeData(
            ClassCollectionService classCollectionService,
            StudentService studentService) {
        this.classCollectionService = classCollectionService;
        this.studentService = studentService;
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {

        if (classCollectionService.getAllClassCollections().isEmpty()) {

            ClassCollection mathClass = new ClassCollection(
                    UUID.randomUUID(), "Math Class", "Class focusing on mathematics", new ArrayList<>());
            ClassCollection scienceClass = new ClassCollection(
                    UUID.randomUUID(), "Science Class", "Class focusing on science", new ArrayList<>());

            System.out.println(scienceClass);
            classCollectionService.createClassCollection(mathClass);
            classCollectionService.createClassCollection(scienceClass);

            Student student1 = new Student(UUID.randomUUID(), "John", "Doe", 15, mathClass);
            Student student2 = new Student(UUID.randomUUID(), "Jane", "Smith", 16, scienceClass);
            Student student3 = new Student(UUID.randomUUID(), "Alice", "Johnson", 14, mathClass);
            Student student4 = new Student(UUID.randomUUID(), "Bob", "Brown", 17, scienceClass);

            studentService.createStudent(student1);
            studentService.createStudent(student2);
            studentService.createStudent(student3);
            studentService.createStudent(student4);

            mathClass.getStudents().add(student1);
            mathClass.getStudents().add(student3);
            scienceClass.getStudents().add(student2);
            scienceClass.getStudents().add(student4);

            classCollectionService.updateClassCollection(mathClass.getId(), mathClass);
            classCollectionService.updateClassCollection(scienceClass.getId(), scienceClass);

            System.out.println("Database initialized with example data.");
        }
    }
}