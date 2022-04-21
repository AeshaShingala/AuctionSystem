package com.simformsolutions.auction.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int token_id;
	private String email;
	private String token;
	public int getToken_id() {
		return token_id;
	}
	public void setToken_id(int token_id) {
		this.token_id = token_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserToken(int token_id, String email, String token) {
		super();
		this.token_id = token_id;
		this.email = email;
		this.token = token;
	}
	public UserToken() {
		super();
	}
	public UserToken(String email, String token) {
		super();
		this.email = email;
		this.token = token;
	}
	
}
