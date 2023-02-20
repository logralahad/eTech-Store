package com.ulsa.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String name;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;

	@Column(columnDefinition = "NUMERIC(18,2)", nullable = false)
	private float price; 

	@Column(columnDefinition = "VARCHAR(75)[]", nullable = false)
	private List<String> tags;

	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATE")
	private Date created_at;

	public Product() {
	}

	public Product(String id, String name, String description, float price, List<String> tags, Date created_at) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.tags = tags;
		this.created_at = created_at;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

}
