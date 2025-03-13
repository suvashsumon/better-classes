package com.suvash.betterclasses.repository;

import com.suvash.betterclasses.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String roleUser);
}