package com.example.schoollab.school.service;

import com.example.schoollab.school.entity.ClassCollection;
import com.example.schoollab.school.entity.Student;
import com.example.schoollab.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(UUID studentId, Student student) {
        Optional<Student> existingStudent = studentRepository.findById(studentId);
        if (existingStudent.isPresent()) {
            Student updatedStudent = existingStudent.get();
            updatedStudent.setName(student.getName());
            updatedStudent.setSurname(student.getSurname());
            updatedStudent.setAge(student.getAge());
            updatedStudent.setClassCollection(student.getClassCollection());
            return studentRepository.save(updatedStudent);
        } else {
            throw new IllegalArgumentException("Student not found with ID: " + studentId);
        }
    }

    public void deleteStudent(UUID studentId) {
        studentRepository.deleteById(studentId);
    }

    public Student getStudentById(UUID studentId) {
        return studentRepository.findById(studentId)
                .orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public List<Student> getAllStudentsByClassCollectionName(String classCollectionName) {
        return studentRepository.findByClassCollectionName(classCollectionName);
    }
}