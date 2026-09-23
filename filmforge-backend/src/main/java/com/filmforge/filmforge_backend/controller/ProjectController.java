package com.filmforge.filmforge_backend.controller;

import com.filmforge.filmforge_backend.dto.ProjectDTO;
import com.filmforge.filmforge_backend.dto.ProjectRequestDTO;
import com.filmforge.filmforge_backend.entity.Project;
import com.filmforge.filmforge_backend.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // CREATE PROJECT
    @PostMapping
    public ProjectDTO createProject(@RequestBody ProjectRequestDTO dto) {
        return projectService.createProject(dto);
    }

    // GET ALL PROJECTS
    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    // GET PROJECT BY ID
    @GetMapping("/{id}")
    public ProjectDTO getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }


    @DeleteMapping("/{id}")
    public String deleteProjectById(@PathVariable Long id) {
        return projectService.deleteProjectById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(
            @PathVariable Long id,
            @RequestBody ProjectRequestDTO dto) {

            ProjectDTO updatedProject = projectService.updateProject(id, dto);
            return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }
}
