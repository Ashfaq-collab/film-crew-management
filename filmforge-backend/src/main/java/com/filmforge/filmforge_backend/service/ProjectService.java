package com.filmforge.filmforge_backend.service;

import com.filmforge.filmforge_backend.customexception.ResourceNotFoundException;
import com.filmforge.filmforge_backend.dto.ProjectDTO;
import com.filmforge.filmforge_backend.dto.ProjectRequestDTO;
import com.filmforge.filmforge_backend.entity.Project;
import com.filmforge.filmforge_backend.entity.User;
import com.filmforge.filmforge_backend.repository.ProjectRepository;
import com.filmforge.filmforge_backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    // Get all projects
    public List<ProjectDTO> getAllProjects() {

        List<Project> projects = projectRepository.findAll();

        return projects.stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Get project by ID
    public ProjectDTO getProjectById(Long id) {

        Project project = projectRepository
                .findById(id)
                .orElse(null);

        if (project == null) {
            return null;
        }

        return convertToDTO(project);
    }

    public ProjectDTO updateProject(Long id, ProjectRequestDTO dto) {
        // 1. Find existing record
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        // 2. Update fields (only if they are provided)
        if (dto.getTitle() != null) {
            existingProject.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            existingProject.setDescription(dto.getDescription());
        }
        if(dto.getGenre() != null) {
            existingProject.setGenre(dto.getGenre());
        }
        if (dto.getStatus()!=null) {
            existingProject.setStatus(dto.getStatus());
        }
        if(dto.getStartDate()!=null) {
            existingProject.setStartDate(dto.getStartDate());
        }
        if(dto.getEndDate()!=null) {
            existingProject.setEndDate(dto.getEndDate());
        }
        if (dto.getUserId() != null) {

            User user = userRepository
                    .findById(dto.getUserId())
                    .orElse(null);

            existingProject.setUser(user);
        }
        Project savedProject =
                projectRepository.save(existingProject);
        return convertToDTO(savedProject);
    }

    public String deleteProjectById(Long id) {
        projectRepository.deleteById(id);
        return "Project with id " + id + " has been deleted";
    }

    private ProjectDTO convertToDTO(Project project) {

        ProjectDTO dto = new ProjectDTO();

        dto.setTitle(project.getTitle());
        dto.setDescription(project.getDescription());
        dto.setGenre(project.getGenre());
        dto.setStatus(project.getStatus());
        return dto;
    }
    public ProjectDTO createProject(ProjectRequestDTO dto) {

        Project project = new Project();

        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setGenre(dto.getGenre());
        project.setStatus(dto.getStatus());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());

        User user = userRepository
                .findById(dto.getUserId())
                .orElse(null);

        project.setUser(user);

        Project savedProject = projectRepository.save(project);

        return convertToDTO(savedProject);
    }
}