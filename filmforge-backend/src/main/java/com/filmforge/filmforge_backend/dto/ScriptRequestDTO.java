package com.filmforge.filmforge_backend.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ScriptRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Version is required")
    private Integer version;

    private String content;
    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "ProjectID is required")
    private Long projectId;

}
