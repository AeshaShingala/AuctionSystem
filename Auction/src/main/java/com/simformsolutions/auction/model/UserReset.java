package com.simformsolutions.auction.model;

public class UserReset {
	private String email;
	private String password;
	private String repeatPassword;
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
	public String getrepeatPassword() {
		return repeatPassword;
	}
	public void setrepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	public UserReset(String email, String password, String repeatPassword) {
		super();
		this.email = email;
		this.password = password;
		this.repeatPassword = repeatPassword;
	}
	public UserReset() {
		super();
	}
	
}
