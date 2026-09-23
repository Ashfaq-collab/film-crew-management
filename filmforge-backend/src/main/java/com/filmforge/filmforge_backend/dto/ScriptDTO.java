package com.filmforge.filmforge_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScriptDTO {
    private Long id;
    private String title;
    private Integer version;
    private String content;
    private String status;
    private LocalDateTime createdAt;
    private Long projectId;
}
