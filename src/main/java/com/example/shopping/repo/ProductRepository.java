package com.example.shopping.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

