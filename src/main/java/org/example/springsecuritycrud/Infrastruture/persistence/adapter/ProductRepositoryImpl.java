package org.example.springsecuritycrud.Infrastruture.persistence.adapter;

import org.example.springsecuritycrud.Domain.entities.Product;
import org.example.springsecuritycrud.Domain.repository.IProductRepository;
import org.example.springsecuritycrud.Infrastruture.mapper.ProductMapper;
import org.example.springsecuritycrud.Infrastruture.persistence.adapter.springData.SpringProductRepository;
import org.example.springsecuritycrud.Infrastruture.persistence.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements IProductRepository {

    private final SpringProductRepository repository;
    private final ProductMapper mapper;

    public ProductRepositoryImpl(SpringProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Product save(Product product) {
        ProductEntity entity = mapper.domainToEntity(product);
        return mapper.entityToDomain(repository.save(entity));
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
}
