package com.example.shopping.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.entity.Product;
import com.example.shopping.repo.ProductRepository;
import com.example.shopping.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {

	 @Autowired
	    private ProductRepository repository;

	    @Override
	    public Product save(Product product) {
	        return repository.save(product);
	    }

	    @Override
	    public List<Product> findAll() {
	        return repository.findAll();
	    }

	    @Override
	    public Optional<Product> findById(Long id) {
	        return repository.findById(id);
	    }

	    @Override
	    public Product update(Long id, Product product) {
	        return repository.findById(id).map(existing -> {
	            existing.setName(product.getName());
	            existing.setDescription(product.getDescription());
	            existing.setPrice(product.getPrice());
	            existing.setMrp(product.getMrp());
	            existing.setDiscount(product.getDiscount());
	            existing.setStock(product.getStock());
	            existing.setCategory(product.getCategory());
	            existing.setImageData(product.getImageData());
	            existing.setImageType(product.getImageType());
	            return repository.save(existing);
	        }).orElseThrow(() -> new RuntimeException("Product not found"));
	    }

	    @Override
	    public void deleteById(Long id) {
	        repository.deleteById(id);
	    }

}
