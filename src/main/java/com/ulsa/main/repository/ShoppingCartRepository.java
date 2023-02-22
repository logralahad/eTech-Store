package com.ulsa.main.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ulsa.main.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
	
	List<ShoppingCart> findByUser_Id(String userId);
	
	ShoppingCart findByUser_IdAndProduct_Id(String userId, String productId);
	
}
