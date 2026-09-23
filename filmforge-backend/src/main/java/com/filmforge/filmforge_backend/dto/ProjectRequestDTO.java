package com.filmforge.filmforge_backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectRequestDTO {
    private String title;

    private String description;

    private String genre;

    private String status;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long userId;
}
