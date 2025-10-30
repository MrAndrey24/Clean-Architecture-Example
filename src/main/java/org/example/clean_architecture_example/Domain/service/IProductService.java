package org.example.clean_architecture_example.Domain.service;

import org.example.clean_architecture_example.Domain.entities.Product;

import java.util.List;


public interface IProductService {
    Long createProduct(Product product);
    List<Product> getAllProducts();
    Product get(Long id);
    void updateProduct(Product product);
    void deleteProductById(Long id);
}
