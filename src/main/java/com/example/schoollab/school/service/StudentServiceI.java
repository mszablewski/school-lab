package com.example.schoollab.school.service;


import com.example.schoollab.school.entity.Student;

import java.util.List;
import java.util.UUID;

public interface StudentServiceI {

    Student createStudent(Student student);
    Student updateStudent(UUID studentId, Student student);
    void deleteStudent(UUID studentId);
    Student getStudentById(UUID studentId);

    List<Student> getStudentsByClassCollectionId(UUID classCollectionId);
    List<Student> getStudentsByClassCollectionName(String classCollectionName);
}
