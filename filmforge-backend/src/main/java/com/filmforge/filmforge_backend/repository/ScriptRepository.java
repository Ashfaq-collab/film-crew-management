package com.filmforge.filmforge_backend.repository;

import com.filmforge.filmforge_backend.entity.Script;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScriptRepository extends JpaRepository<Script, Long> {
}
