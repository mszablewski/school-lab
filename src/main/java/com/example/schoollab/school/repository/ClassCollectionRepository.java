package com.example.schoollab.school.repository;

import com.example.schoollab.school.entity.ClassCollection;
import com.example.schoollab.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassCollectionRepository extends JpaRepository<ClassCollection, UUID> {

}