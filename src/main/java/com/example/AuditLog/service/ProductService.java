package com.example.AuditLog.service;

import com.example.AuditLog.entity.Product;
import com.example.AuditLog.entity.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public Product createProduct(Product product) {
        // Check if product with the same code already exists
        if (productRepository.findById(product.getCode()).isPresent()) {
            throw new IllegalArgumentException("Product with code " + product.getCode() + " already exists");
        }
        return productRepository.save(product);
    }
}
