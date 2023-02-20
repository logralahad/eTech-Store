package com.ulsa.entity;

import java.util.Set;

import jakarta.annotation.*;
import jakarta.persistence.*;

@Entity
@Table(name = "colors")
public class Color{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Nonnull
	private String name;
	
	@Nonnull
	private float plus_price;
	
	@Nonnull
	@ManyToMany(targetEntity = Product.class)
	private Set<Product> products;
	
	public Color() {}

	public Color(long id, String name, float plus_price, Set<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.plus_price = plus_price;
		this.products = products;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPlus_price() {
		return plus_price;
	}

	public void setPlus_price(float plus_price) {
		this.plus_price = plus_price;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> product) {
		this.products = product;
	}

}
