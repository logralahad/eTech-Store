package com.ulsa.main.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wishlist")
public class Wishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	public Wishlist() {}

	public Wishlist(long id, Product product, User user) {
		super();
		this.id = id;
		this.product = product;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
