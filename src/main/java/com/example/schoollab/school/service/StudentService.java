package com.example.schoollab.school.service;

import com.example.schoollab.school.entity.Student;
import com.example.schoollab.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService implements StudentServiceI {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
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

    @Override
    public void deleteStudent(UUID studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public Student getStudentById(UUID studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));
    }

    // Additional Query Methods
    @Override
    public List<Student> getStudentsByClassCollectionId(UUID classCollectionId) {
        return studentRepository.findByClassCollectionId(classCollectionId);
    }

    @Override
    public List<Student> getStudentsByClassCollectionName(String classCollectionName) {
        return studentRepository.findByClassCollectionName(classCollectionName);
    }
}