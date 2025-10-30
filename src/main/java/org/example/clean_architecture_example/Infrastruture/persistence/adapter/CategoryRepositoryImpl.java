package org.example.clean_architecture_example.Infrastruture.persistence.adapter;

import org.example.clean_architecture_example.Domain.entities.Category;
import org.example.clean_architecture_example.Domain.repository.ICategoryRepository;
import org.example.clean_architecture_example.Infrastruture.mapper.CategoryMapper;
import org.example.clean_architecture_example.Infrastruture.persistence.repository.CategoryRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CategoryRepositoryImpl implements ICategoryRepository {

    private final CategoryRepositoryJpa repository;
    private final CategoryMapper mapper;

    public CategoryRepositoryImpl(CategoryRepositoryJpa repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Category save(Category category) {
        var entity = mapper.domainToEntity(category);
        var savedEntity = repository.save(entity);
        return mapper.entityToDomain(savedEntity);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return repository.findByName(name)
                .map(mapper::entityToDomain);
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
