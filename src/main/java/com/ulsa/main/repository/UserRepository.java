package com.ulsa.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ulsa.main.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsername(String username);
	    
}