package com.filmforge.filmforge_backend.service;

import com.filmforge.filmforge_backend.customexception.ResourceNotFoundException;
import com.filmforge.filmforge_backend.dto.ProjectDTO;
import com.filmforge.filmforge_backend.dto.ProjectRequestDTO;
import com.filmforge.filmforge_backend.dto.ScriptDTO;
import com.filmforge.filmforge_backend.dto.ScriptRequestDTO;
import com.filmforge.filmforge_backend.entity.Project;
import com.filmforge.filmforge_backend.entity.Script;
import com.filmforge.filmforge_backend.entity.User;
import com.filmforge.filmforge_backend.repository.ProjectRepository;
import com.filmforge.filmforge_backend.repository.ScriptRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScriptService {
    private final ScriptRepository scriptRepository;
    private final ProjectRepository projectRepository;

    public ScriptService(ScriptRepository scriptRepository,
                         ProjectRepository projectRepository) {
        this.scriptRepository = scriptRepository;
        this.projectRepository = projectRepository;
    }

    // CREATE
    public ScriptDTO createScript(ScriptRequestDTO dto) {
        Script script = new Script();
        script.setTitle(dto.getTitle());
        script.setVersion(dto.getVersion());
        script.setContent(dto.getContent());
        script.setStatus(dto.getStatus());
        if (dto.getProjectId() != null) {

            Project project = projectRepository
                    .findById(dto.getProjectId())
                    .orElseThrow(()->new ResourceNotFoundException("ProjectId Id "+dto.getProjectId()+" Not Found"));

            script.setProject(project);
        }
        Script savedScript = scriptRepository.save(script);

        return convertToDTO(savedScript);
    }

    // GET ALL
    public List<ScriptDTO> getAllScripts() {

        return scriptRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    // GET BY ID
    public ScriptDTO getScriptById(Long id) {

        Script script = scriptRepository.findById(id).orElse(null);

        if (script == null) {
            return null;
        }

        return convertToDTO(script);
    }
    //UPDATE
    public ScriptDTO  updateScript(Long id, ScriptRequestDTO dto) {
        // 1. Find existing record
        Script  existingScript = scriptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Script not found with id: " + id));

        // 2. Update fields (only if they are provided)
        if (dto.getTitle() != null) {
            existingScript.setTitle(dto.getTitle());
        }
        if (dto.getVersion() != null) {
            existingScript.setVersion(dto.getVersion());
        }
        if(dto.getContent() != null) {
            existingScript.setContent(dto.getContent());
        }
        if (dto.getStatus()!=null) {
            existingScript.setStatus(dto.getStatus());
        }
        if (dto.getProjectId() != null) {

            Project project = projectRepository
                    .findById(dto.getProjectId())
                    .orElseThrow(()->new ResourceNotFoundException("ProjectId Id "+dto.getProjectId()+" Not Found"));

            existingScript.setProject(project);
        }
        Script savedScript = scriptRepository.save(existingScript);

        return convertToDTO(savedScript);
    }

    // DELETE
    public String deleteScriptById(Long id) {

        scriptRepository.deleteById(id);

        return "Script with id " + id + " has been deleted";
    }

    public ScriptDTO convertToDTO(Script script) {
        ScriptDTO dto = new ScriptDTO();
        dto.setId(script.getId());
        dto.setTitle(script.getTitle());
        dto.setVersion(script.getVersion());
        dto.setContent(script.getContent());
        dto.setStatus(script.getStatus());
        dto.setProjectId(script.getProject().getId());
        return dto;
    }
}
