package com.example.schoollab.school.service;

import com.example.schoollab.school.entity.ClassCollection;
import com.example.schoollab.school.repository.ClassCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClassCollectionService {
    private final ClassCollectionRepository classCollectionRepository;

    @Autowired
    public ClassCollectionService(ClassCollectionRepository classCollectionRepository) {
        this.classCollectionRepository = classCollectionRepository;
    }

    public ClassCollection createClassCollection(ClassCollection classCollection) {
        return classCollectionRepository.save(classCollection);
    }

    public ClassCollection updateClassCollection(UUID id, ClassCollection classCollection) {
        Optional<ClassCollection> existingClassCollection = classCollectionRepository.findById(id);
        if (existingClassCollection.isPresent()) {
            ClassCollection updatedClassCollection = existingClassCollection.get();
            updatedClassCollection.setName(classCollection.getName());
            updatedClassCollection.setDescription(classCollection.getDescription());
            return classCollectionRepository.save(updatedClassCollection);
        } else {
            throw new IllegalArgumentException("ClassCollection not found with ID: " + id);
        }
    }

    public void deleteClassCollection(UUID id) {
        classCollectionRepository.deleteById(id);
    }

    public ClassCollection getClassCollectionById(UUID id) {
        return classCollectionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ClassCollection not found with ID: " + id));
    }

    public List<ClassCollection> getAllClassCollections() {
        return classCollectionRepository.findAll();
    }
}