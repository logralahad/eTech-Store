package com.ulsa.main.entity;

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
	private String hexa;
	
	@Nonnull
	@ManyToMany(targetEntity = Product.class)
	private Set<Product> products;
	
	public Color() {}

	public Color(long id, String name, String hexa, Set<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.hexa = hexa;
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

	public String getHexa() {
		return hexa;
	}

	public void setHexa(String hexa) {
		this.hexa = hexa;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
