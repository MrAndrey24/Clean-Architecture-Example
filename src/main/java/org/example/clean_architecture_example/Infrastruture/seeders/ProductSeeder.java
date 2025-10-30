package org.example.clean_architecture_example.Infrastruture.seeders;

import org.example.clean_architecture_example.Domain.entities.Category;
import org.example.clean_architecture_example.Domain.entities.Product;
import org.example.clean_architecture_example.Domain.repository.ICategoryRepository;
import org.example.clean_architecture_example.Domain.repository.IProductRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Order(2)
@Component
public class ProductSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    public ProductSeeder(IProductRepository productRepository, ICategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        seeIfProductExists();
    }

    private void seeIfProductExists() {
        if (!productRepository.existsProducts()) {
            createProducts();
        }
    }

    @Transactional
    protected void createProducts() {

        Category electronics = categoryRepository.findByName("Electronics").orElseThrow(()->new RuntimeException("Category Electronics not found"));
        Category clothing = categoryRepository.findByName("Clothing").orElseThrow(()->new RuntimeException("Category Clothing not found"));
        Category books = categoryRepository.findByName("Books").orElseThrow(()->new RuntimeException("Category books not found"));
        Category accessories = categoryRepository.findByName("Accessories").orElseThrow(()->new RuntimeException("Category accessories not found"));


        List<Product> products = List.of(
                new Product("Smartphone", "Latest model with advanced features", 699.99, 50, electronics),
                new Product("Laptop", "High-performance laptop for work and play", 1299.99, 30, electronics),
                new Product("Wireless Headphones", "Noise-cancelling headphones with great sound quality", 199.99, 100, accessories),
                new Product("E-Book Reader", "Portable device for reading digital books", 129.99, 10 ,books),
                new Product("T-Shirt", "Comfortable cotton t-shirt", 19.99, 200, clothing)
        );

        products.stream()
                .filter(product -> productRepository.findByName(product.getName()).isEmpty())
                .forEach(productRepository::save);

    }
}
