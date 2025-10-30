package org.example.clean_architecture_example.Infrastruture.persistence.adapter;

import org.example.clean_architecture_example.Domain.entities.Product;
import org.example.clean_architecture_example.Domain.repository.IProductRepository;
import org.example.clean_architecture_example.Infrastruture.mapper.ProductMapper;
import org.example.clean_architecture_example.Infrastruture.persistence.repository.ProductRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements IProductRepository {

    private final ProductRepositoryJpa repository;
    private final ProductMapper mapper;

    public ProductRepositoryImpl(ProductRepositoryJpa repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Product save(Product product) {
        var entity = mapper.domainToEntity(product);
        var savedEntity = repository.save(entity);
        return mapper.entityToDomain(savedEntity);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(mapper::entityToDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id).map(mapper::entityToDomain);
    }

    @Override
    public void deletebyId(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsProducts() {
        return repository.count() != 0;
    }
}
