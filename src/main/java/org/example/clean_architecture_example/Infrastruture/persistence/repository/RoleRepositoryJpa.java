package org.example.clean_architecture_example.Infrastruture.persistence.repository;

import org.example.clean_architecture_example.Domain.enums.RoleEnum;
import org.example.clean_architecture_example.Infrastruture.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepositoryJpa extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(RoleEnum name);
}
