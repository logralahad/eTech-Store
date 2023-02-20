package com.ulsa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 

	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String provider;
	
	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String transaction_id;
	
	@OneToOne(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Order order;

	public Payment() {}

	public Payment(long id, String provider, String transaction_id, Order order) {
		super();
		this.id = id;
		this.provider = provider;
		this.transaction_id = transaction_id;
		this.order = order;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
