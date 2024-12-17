package com.example.schoollab.school.controller;

import com.example.schoollab.school.dto.StudentCreateDto;
import com.example.schoollab.school.dto.StudentDto;
import com.example.schoollab.school.entity.Student;
import com.example.schoollab.school.service.ClassCollectionService;
import com.example.schoollab.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    private final ClassCollectionService classCollectionService;

    @Autowired
    public StudentController(StudentService studentService, ClassCollectionService classCollectionService) {
        this.studentService = studentService;
        this.classCollectionService = classCollectionService;
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentCreateDto studentCreateDto) {
        var student = Student.builder()
                .name(studentCreateDto.getName())
                .surname(studentCreateDto.getSurname())
                .age(studentCreateDto.getAge())
                .classCollection(classCollectionService.getClassCollectionById(studentCreateDto.getClassCollectionId()))
                .build();
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent.toStudentDto());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable UUID studentId) {
        try {
            Student student = studentService.getStudentById(studentId);
            return ResponseEntity.ok(student.toStudentDto());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID studentId) {
        try {
            studentService.deleteStudent(studentId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents()
                .stream()
                .map(Student::toStudentDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(students);
    }
}