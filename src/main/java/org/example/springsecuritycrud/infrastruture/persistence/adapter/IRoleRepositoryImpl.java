package org.example.springsecuritycrud.infrastruture.persistence.adapter;

import org.example.springsecuritycrud.domain.entities.Role;
import org.example.springsecuritycrud.domain.enums.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepositoryImpl extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}