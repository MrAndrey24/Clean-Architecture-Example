package org.example.clean_architecture_example.Application.service;

import org.example.clean_architecture_example.Domain.entities.Category;
import org.example.clean_architecture_example.Domain.repository.ICategoryRepository;
import org.example.clean_architecture_example.Domain.service.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl  implements ICategoryService {
    private final ICategoryRepository repo;

    public CategoryServiceImpl(ICategoryRepository repo) {
        this.repo = repo;
    }


    @Override
    public Long createCategory(Category category) {
        if (repo.findByName(category.getName()) != null && !repo.findByName(category.getName()).isEmpty()){
            throw new RuntimeException("Category with name " + category.getName() + " already exists");
        }
        repo.save(category);
        return category.getId();
    }

    @Override
    public List<Category> getAllCategories() {
        return repo.findAll();
    }

    @Override
    public Category get(Long id) {
        Optional<Category> category = repo.findById(id);
        return  category.orElse(null);
    }

    @Override
    public void updateCategory(Category category) {
        repo.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        repo.deleteById(id);
    }
}
