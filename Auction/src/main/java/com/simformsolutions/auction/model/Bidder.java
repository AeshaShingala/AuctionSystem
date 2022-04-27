package com.simformsolutions.auction.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Bidder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bidderId;
	private String name;
	private String email;
	private String password;
	private long contact;
	
	@OneToMany(targetEntity = Lot.class, cascade = CascadeType.ALL)
	@JoinColumn(name="fkBidderId",referencedColumnName = "bidderId")
	private List<Lot> lots;
	
	public int getBidderId() {
		return bidderId;
	}
	public void setBidderId(int bidderId) {
		this.bidderId = bidderId;
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
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public List<Lot> getLots() {
		return lots;
	}
	public void setLots(List<Lot> lots) {
		this.lots = lots;
	}
	public Bidder(int bidderId, String name, String email, String password, long contact, List<Lot> lots) {
		super();
		this.bidderId = bidderId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.lots = lots;
	}
	public Bidder() {
		super();
	}
	
}
