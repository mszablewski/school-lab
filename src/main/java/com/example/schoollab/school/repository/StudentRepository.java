package com.example.schoollab.school.repository;

import com.example.schoollab.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    List<Student> findByClassCollectionId(UUID classCollectionId);

    List<Student> findByClassCollectionName(String classCollectionName);
}