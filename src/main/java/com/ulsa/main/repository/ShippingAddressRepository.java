package com.ulsa.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ulsa.main.entity.ShipmentAddress;

public interface ShippingAddressRepository extends JpaRepository<ShipmentAddress, Long> {
	
}
