package org.example.springsecuritycrud.Domain.service;

import org.example.springsecuritycrud.Domain.entities.Category;

import java.util.List;

public interface ICategoryService {
    Long createCategory(Category category);
    List<Category> getAllCategories();
    Category get(Long id);
    void updateCategory(Category category);
    void deleteCategoryById(Long id);
}
