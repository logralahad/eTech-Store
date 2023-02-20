package com.ulsa.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String name;

	@Column(columnDefinition = "VARCHAR(50)[]", nullable = false)
	private List<String> access;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users; 
	
	public Role() {}

	public Role(long id, List<User> users, String name, List<String> access) {
		super();
		this.id = id;
		this.users = users;
		this.name = name; 
		this.access = access;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAccess() {
		return access;
	}

	public void setAccess(List<String> access) {
		this.access = access;
	} 
	
}
