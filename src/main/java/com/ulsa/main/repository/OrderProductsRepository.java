package com.ulsa.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ulsa.main.entity.OrderProducts;

public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {
	
}
