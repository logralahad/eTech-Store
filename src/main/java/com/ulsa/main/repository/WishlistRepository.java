package com.ulsa.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ulsa.main.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
	
	List<Wishlist> findByUser_Id(String userId);
	
	Wishlist findByUser_IdAndProduct_Id(String userId, String productId);
	
}
