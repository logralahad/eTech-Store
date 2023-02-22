package com.ulsa.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ulsa.main.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	
	Optional<Product> findByName(String name);
	
	Optional<Product> findByDescription(String description);
	
}
