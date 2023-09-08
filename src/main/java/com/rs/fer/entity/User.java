package com.rs.fer.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="user_annotations")
public class User {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String firstname;
	private String middlename;
	private String lastname;
	private String username;
	private String password;
	private String Email;
	private String mobile;
	
	private String dob;
	@Transient
	private int age;


	//private int addid;
	
	@OneToOne(cascade = CascadeType.ALL,targetEntity = Address.class)	
	@JoinColumn(name="addrid" ,referencedColumnName ="address_id")

	private Address Address;

	@OneToMany(cascade=CascadeType.ALL,targetEntity = Expense.class, fetch = FetchType.LAZY)
	@JoinColumn(name="uid" ,referencedColumnName ="user_id")
	private Set<Expense> expenses;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Address getAddress() {
		return Address;
	}

	public void setAddress(Address address) {
		Address = address;
	}

	public void setExpenses(Set<Expense> expenses) {
		this.expenses = expenses;
	}

	public Set<Expense> getExpenses() {
		return expenses;
	}

	/*
	 * public int getAddid() { return addid; }
	 * 
	 * public void setAddid(int addid) { this.addid = addid; }
	 */

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

}
