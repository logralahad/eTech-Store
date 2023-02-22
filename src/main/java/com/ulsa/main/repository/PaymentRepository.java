package com.ulsa.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ulsa.main.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	Optional<Payment> findByProvider(String name);
	
}
