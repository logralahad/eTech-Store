package com.ulsa.main.entity;

import java.util.Set;

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
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderProducts> order_products;
	
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    public Order() {}

	public Order(long id, float total, String shipment_type, ShipmentAddress shipment_address, Payment payment,
			Set<OrderProducts> order_products, User user) {
		super();
		this.id = id;
		this.total = total;
		this.shipment_type = shipment_type;
		this.shipment_address = shipment_address;
		this.payment = payment;
		this.order_products = order_products;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getShipment_type() {
		return shipment_type;
	}

	public void setShipment_type(String shipment_type) {
		this.shipment_type = shipment_type;
	}

	public ShipmentAddress getShipment_address() {
		return shipment_address;
	}

	public void setShipment_address(ShipmentAddress shipment_address) {
		this.shipment_address = shipment_address;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderProducts> getOrder_products() {
		return order_products;
	}

	public void setOrder_products(Set<OrderProducts> order_products) {
		this.order_products = order_products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
