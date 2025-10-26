package org.example.springsecuritycrud.Application.service;


import org.example.springsecuritycrud.Domain.entities.Product;
import org.example.springsecuritycrud.Domain.repository.IProductRepository;
import org.example.springsecuritycrud.Domain.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository repo;

    public ProductServiceImpl(IProductRepository repo) {
        this.repo = repo;
    }


    @Override
    public Long createProduct(Product product) {
        if (repo.findByName(product.getName()).isPresent()) {
            throw new RuntimeException("Product with name " + product.getName() + "already exists");
        }
        repo.save(product);
        return product.getId();
    }

    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public Product get(Long id) {
        Optional<Product> product = repo.findById(id);
        return product.orElse(null);
    }

    @Override
    public void updateProduct(Product product) {
        repo.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        repo.deletebyId(id);
    }
}
