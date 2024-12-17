package com.example.schoollab.school.repository;

import com.example.schoollab.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    @Query("SELECT s FROM Student s WHERE s.classCollection.name = :name")
    List<Student> findByClassCollectionName(String name);
}