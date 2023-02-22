package com.ulsa.main.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	
	@Column(columnDefinition = "INTEGER", nullable = false)
	private int quantity; 
	
	@ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	public ShoppingCart() {}

	public ShoppingCart(long id, int quantity, Product product, User user) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.product = product;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
