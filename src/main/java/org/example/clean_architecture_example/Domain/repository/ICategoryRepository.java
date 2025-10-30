package org.example.clean_architecture_example.Domain.repository;

import org.example.clean_architecture_example.Domain.entities.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository {
    Category save(Category category);
    Optional<Category> findByName(String name);
    List<Category> findAll();
    Optional<Category> findById(Long id);
    void deleteById(Long id);
}
