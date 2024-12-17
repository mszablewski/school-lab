package com.example.schoollab.school.controller;

import com.example.schoollab.school.dto.ClassCollectionCreateDto;
import com.example.schoollab.school.dto.ClassCollectionDto;
import com.example.schoollab.school.entity.ClassCollection;
import com.example.schoollab.school.service.ClassCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/class-collections")
public class ClassCollectionController {

    private final ClassCollectionService classCollectionService;

    @Autowired
    public ClassCollectionController(ClassCollectionService classCollectionService) {
        this.classCollectionService = classCollectionService;
    }

    @PutMapping("/{classCollectionId}")
    public ResponseEntity<ClassCollectionDto> createOrUpdateClassCollection(
            @PathVariable UUID classCollectionId,
            @RequestBody ClassCollectionCreateDto classCollectionCreateDto) {
        boolean exists = classCollectionService.getClassCollectionById(classCollectionId) != null;

        var classCollection = ClassCollection.builder()
                .id(classCollectionId)
                .name(classCollectionCreateDto.getName())
                .description(classCollectionCreateDto.getDescription())
                .build();

        ClassCollection savedClassCollection;
        HttpStatus status;

        if (exists) {
            savedClassCollection = classCollectionService.updateClassCollection(classCollectionId, classCollection);
            status = HttpStatus.OK;
        } else {
            savedClassCollection = classCollectionService.createClassCollection(classCollection);
            status = HttpStatus.CREATED;
        }

        return ResponseEntity.status(status).body(toClassCollectionDto(savedClassCollection));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassCollection(@PathVariable UUID id) {
        try {
            classCollectionService.deleteClassCollection(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ClassCollectionDto>> getAllClassCollections() {
        List<ClassCollectionDto> classCollections = classCollectionService.getAllClassCollections()
                .stream()
                .map(this::toClassCollectionDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(classCollections);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassCollectionDto> getClassCollectionById(@PathVariable UUID id) {
        try {
            ClassCollection classCollection = classCollectionService.getClassCollectionById(id);
            return ResponseEntity.ok(toClassCollectionDto(classCollection));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private ClassCollectionDto toClassCollectionDto(ClassCollection classCollection) {
        return ClassCollectionDto.builder()
                .id(classCollection.getId())
                .name(classCollection.getName())
                .description(classCollection.getDescription())
                .build();
    }
}