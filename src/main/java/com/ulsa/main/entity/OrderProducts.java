package com.ulsa.main.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_products")
public class OrderProducts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	
	@Column(columnDefinition = "INTEGER", nullable = false)
	private int quantity; 
	
	@ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
	
	@ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
	
	public OrderProducts() {}

	public OrderProducts(long id, int quantity, Product product, Order order) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.product = product;
		this.order = order;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
