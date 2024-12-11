package com.example.schoollab.school.service;

import com.example.schoollab.school.entity.ClassCollection;

import java.util.List;
import java.util.UUID;

public interface ClassCollectionServiceI {
    ClassCollection createClassCollection(ClassCollection classCollection);
    ClassCollection updateClassCollection(UUID id, ClassCollection classCollection);
    void deleteClassCollection(UUID id);
    ClassCollection getClassCollectionById(UUID id);

    List<ClassCollection> getAllClassCollections();
}