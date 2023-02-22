package com.ulsa.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ulsa.main.entity.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {
	
	Optional<Color> findByName(String name);
	
	Optional<Color> findByHexa(String code);
	
}
