package com.customerService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Customer")
public class Customer {
    @Id
    @Column(name = "IDCUSTOMER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IDCATEGORY")
	private Long idCategory;
    
    @Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASS")
	private String password;

	@Column(name = "EMAIL")
	private String email;
	
	public Customer() {
	}
	
	public Customer(Long idCategory, String username, String email, String password) {
        this.idCategory = idCategory;
		this.username = username;
        this.email = email;
        this.password = password;
    }
	
	public Customer(Long id, Long idCategory, String username, String password, String email) {
		this.id = id;
		this.idCategory = idCategory;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Customer(Long id, String password, String email) {
		this.id = id;
		this.password = password;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}