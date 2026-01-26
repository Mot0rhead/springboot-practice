package com.bolsaideas.springboot.web.app.models;


public class User {

	private String username;
	private String lastname;
	private String email;
	
	public User() {

	}
	public User(String username, String lastname, String email) {
		this.username = username;
		this.lastname = lastname;
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
