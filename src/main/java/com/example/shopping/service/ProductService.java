package com.example.shopping.service;


import java.util.List;
import java.util.Optional;

import com.example.shopping.entity.Product;

public interface ProductService {
    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product update(Long id, Product product);
    void deleteById(Long id);
}

