package com.example.schoollab.school.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ClassCollectionDto {
    private UUID id;
    private String name;
    private String description;
}