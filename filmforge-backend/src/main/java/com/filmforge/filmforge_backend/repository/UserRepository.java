package com.filmforge.filmforge_backend.repository;

import com.filmforge.filmforge_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
