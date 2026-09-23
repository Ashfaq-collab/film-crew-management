package com.filmforge.filmforge_backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDTO {
    private String title;
    private String description;
    private String genre;
    private String status;
}
