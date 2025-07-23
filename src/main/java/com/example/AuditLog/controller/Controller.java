package com.example.AuditLog.controller;

import com.example.AuditLog.entity.Product;
import com.example.AuditLog.entity.ProductRepository;
import com.example.AuditLog.entity.User;
import com.example.AuditLog.entity.UserRepository;
import com.example.AuditLog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product) {

        Product product1 = new Product();
        product1.setCode(product.getCode());
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());

        return productRepository.save(product1);
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {

        User user1 = new User();
        user1.setName(user.getName());
        user1.setPrice(user.getPrice());

        return userRepository.save(user1);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }


    @PostMapping("/update")
    public ResponseEntity<String> updateProductName(@RequestParam Integer id, @RequestParam String name, @RequestParam Double price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
//        product.setCode(product.getCode());

//        productService.updateProduct(id, name, price);

        return ResponseEntity.ok("Product updated successfully.");
    }

//    @DeleteMapping
//    public void deleteProduct(@RequestParam Integer id) {
//        productRepository.deleteById(id);
//    }


    @GetMapping("/all")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

//    @GetMapping("/{id}/audit")
//    public List getProductAuditHistory(@PathVariable String id, @RequestParam String entity) throws ClassNotFoundException {
//        // Convert the entity name to its corresponding Class object
//        Class<?> entityClass = Class.forName("com.example.testing.entity." + entity);
//        return auditService.getAuditHistoryTest(id, entityClass);
//    }

}