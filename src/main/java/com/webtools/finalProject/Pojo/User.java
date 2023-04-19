package com.webtools.finalProject.Pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.OneToOne;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.webtools.finalProject.Pojo.Address;


@Component
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<TravelPackages> travelPackages = new HashSet<>();
	
//	@OneToOne(mappedBy="user", cascade = CascadeType.ALL)
//    private Address address;
	
	public User() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	
	
	

}
