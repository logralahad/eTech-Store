package com.ulsa.main.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "shipment_address")
public class ShipmentAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String street_adress;

	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String state;

	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String country;

	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String phone;

	
	@OneToOne(mappedBy = "shipment_address", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Order order;

	public ShipmentAddress() {
	}

	public ShipmentAddress(long id, String street_adress, String state, String country, String phone, Order order) {
		super();
		this.id = id;
		this.street_adress = street_adress;
		this.state = state;
		this.country = country;
		this.phone = phone;
		this.order = order;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet_adress() {
		return street_adress;
	}

	public void setStreet_adress(String street_adress) {
		this.street_adress = street_adress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
