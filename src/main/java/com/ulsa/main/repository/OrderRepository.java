package com.ulsa.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ulsa.main.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	Optional<Order> findByUserId(String userId);
	
}
