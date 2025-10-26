package org.example.springsecuritycrud.Domain.repository;

import org.example.springsecuritycrud.Domain.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    Product save(Product product);
    Optional<Product> findByName(String name);
    List<Product> findAll();
    Optional<Product> findById(Long id);
    void deletebyId(Long id);
}
