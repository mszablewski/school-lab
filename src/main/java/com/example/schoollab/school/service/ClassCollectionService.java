package com.example.schoollab.school.service;

import com.example.schoollab.school.entity.ClassCollection;
import com.example.schoollab.school.repository.ClassCollectionRepository;
import com.example.schoollab.school.service.ClassCollectionServiceI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClassCollectionService implements ClassCollectionServiceI {
    private final ClassCollectionRepository classCollectionRepository;

    public ClassCollectionService(ClassCollectionRepository classCollectionRepository) {
        this.classCollectionRepository = classCollectionRepository;
    }

    @Override
    public ClassCollection createClassCollection(ClassCollection classCollection) {
        return classCollectionRepository.save(classCollection);
    }

    @Override
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

    @Override
    public void deleteClassCollection(UUID id) {
        classCollectionRepository.deleteById(id);
    }

    @Override
    public ClassCollection getClassCollectionById(UUID id) {
        return classCollectionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ClassCollection not found with ID: " + id));
    }

    @Override
    public List<ClassCollection> getAllClassCollections() {
        return classCollectionRepository.findAll();
    }
}