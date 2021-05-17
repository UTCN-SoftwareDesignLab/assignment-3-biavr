package com.example.clinic.user;

import com.example.clinic.user.model.ERole;
import com.example.clinic.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
}
