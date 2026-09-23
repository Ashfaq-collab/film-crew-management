package com.filmforge.filmforge_backend.controller;

import com.filmforge.filmforge_backend.dto.ScriptDTO;
import com.filmforge.filmforge_backend.dto.ScriptRequestDTO;
import com.filmforge.filmforge_backend.entity.Script;
import com.filmforge.filmforge_backend.service.ScriptService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scripts")
public class ScriptController {
    private final ScriptService scriptService;

    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> createScript(@Valid @RequestBody ScriptRequestDTO dto) {

        return new  ResponseEntity<>(scriptService.createScript(dto), HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public List<ScriptDTO> getAllScripts() {

        return scriptService.getAllScripts();
    }
    // GET BY ID
    @GetMapping("/{id}")
    public ScriptDTO getScriptById(
            @PathVariable Long id) {

        return scriptService.getScriptById(id);
    }
    // UPDATE
    @PutMapping("/{id}")
    public ScriptDTO updateScript(
            @PathVariable Long id, @Valid @RequestBody ScriptRequestDTO dto) {

        return scriptService.updateScript(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteScriptById(
            @PathVariable Long id) {

        return scriptService.deleteScriptById(id);
    }}
