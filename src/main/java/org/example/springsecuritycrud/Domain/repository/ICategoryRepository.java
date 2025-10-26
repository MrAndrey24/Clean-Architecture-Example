package org.example.springsecuritycrud.Domain.repository;

import org.example.springsecuritycrud.Domain.entities.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository {
    Category save(Category category);
    List<Category> findByName(String name);
    List<Category> findAll();
    Optional<Category> findById(Long id);
    void deleteById(Long id);
}
