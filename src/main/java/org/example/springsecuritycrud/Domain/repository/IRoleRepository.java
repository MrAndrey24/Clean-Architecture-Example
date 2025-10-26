package org.example.springsecuritycrud.Domain.repository;

import org.example.springsecuritycrud.Domain.entities.Role;
import org.example.springsecuritycrud.Domain.enums.RoleEnum;

import java.util.Optional;

public interface IRoleRepository {
    Role save(Role role);
    Optional<Role> findByName(RoleEnum name);
}
