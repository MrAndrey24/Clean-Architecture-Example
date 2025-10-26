package org.example.springsecuritycrud.Infrastruture.persistence.adapter.springData;

import org.example.springsecuritycrud.Domain.enums.RoleEnum;
import org.example.springsecuritycrud.Infrastruture.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringRoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(RoleEnum name);
}
