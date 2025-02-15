package com.suvash.betterclasses.Repository;

import com.suvash.betterclasses.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String roleUser);
}