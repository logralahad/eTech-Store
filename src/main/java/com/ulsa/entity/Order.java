package com.ulsa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 

	@Column(columnDefinition = "NUMERIC(18,2)", nullable = false)
	private float total;
	
	@Column(columnDefinition = "VARCHAR(150)", nullable = false)
	private String shipment_type;
	
	@JoinColumn(name = "shipment_address_id")
	@OneToOne(fetch = FetchType.LAZY)
    private ShipmentAddress shipment_address;
	
	@JoinColumn(name = "payment_id")
	@OneToOne(fetch = FetchType.LAZY)
    private Payment payment;
	
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
}
