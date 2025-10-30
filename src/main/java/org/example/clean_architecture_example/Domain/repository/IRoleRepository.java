package org.example.clean_architecture_example.Domain.repository;

import org.example.clean_architecture_example.Domain.entities.Role;
import org.example.clean_architecture_example.Domain.enums.RoleEnum;

import java.util.Optional;

public interface IRoleRepository {
    Role save(Role role);
    Optional<Role> findByName(RoleEnum name);
}
