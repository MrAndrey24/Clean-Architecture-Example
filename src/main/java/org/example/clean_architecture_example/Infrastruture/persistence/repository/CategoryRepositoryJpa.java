package org.example.clean_architecture_example.Infrastruture.persistence.repository;

import org.example.clean_architecture_example.Domain.entities.Category;
import org.example.clean_architecture_example.Infrastruture.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepositoryJpa extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
    void save(Category category);
}
