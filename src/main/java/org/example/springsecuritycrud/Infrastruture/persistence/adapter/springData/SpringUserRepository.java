package org.example.springsecuritycrud.Infrastruture.persistence.adapter.springData;

import org.example.springsecuritycrud.Infrastruture.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);
    Optional<UserEntity> findByEmail(String email);
}
