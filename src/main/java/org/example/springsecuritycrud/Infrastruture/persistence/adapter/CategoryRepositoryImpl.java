package org.example.springsecuritycrud.Infrastruture.persistence.adapter;

import org.example.springsecuritycrud.Domain.entities.Category;
import org.example.springsecuritycrud.Domain.repository.ICategoryRepository;
import org.example.springsecuritycrud.Infrastruture.mapper.CategoryMapper;
import org.example.springsecuritycrud.Infrastruture.persistence.adapter.springData.SpringCategoryRepository;
import org.example.springsecuritycrud.Infrastruture.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CategoryRepositoryImpl implements ICategoryRepository {

    private final SpringCategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryRepositoryImpl(SpringCategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Category save(Category category) {
        CategoryEntity entity = mapper.domainToEntity(category);
        return mapper.entityToDomain(repository.save(entity));
    }

    @Override
    public List<Category> findByName(String name) {
        return List.of();
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findById(Long id) {
        return repository.findById(id).map(
                mapper::entityToDomain
        );
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
