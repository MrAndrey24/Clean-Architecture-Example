package org.example.clean_architecture_example.Domain.service;

import org.example.clean_architecture_example.Domain.entities.Category;

import java.util.List;

public interface ICategoryService {
    Long createCategory(Category category);
    List<Category> getAllCategories();
    Category get(Long id);
    void updateCategory(Category category);
    void deleteCategoryById(Long id);
}
