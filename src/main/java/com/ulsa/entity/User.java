package com.ulsa.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID )
	private String id;
	

	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String username;

	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String first_name;
	
	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String last_name;

	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String email;
	
	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String password;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT TRUE", nullable = false)
	private boolean is_active;

	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATE")
	private Date created_at;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders; 
	
	@ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
	
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "wishlist",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Set<Product> products;
	
	public User() {}

	public User(String id, Role role, String username, String first_name, String last_name, String email,
			String password, boolean is_active, Date created_at) {
		super();
		this.id = id;
		this.role = role;
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.is_active = is_active;
		this.created_at = created_at;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
}
