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
public class Auctioneer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int auctioneerId;
	private String name;
	private String email;
	private String password;
	private long contact;
	
	@OneToMany(targetEntity = Auction.class, cascade = CascadeType.ALL)
	@JoinColumn(name= "fkAuctioneerId", referencedColumnName = "auctioneerId")
	private List<Auction> auctions;
	
	public int getAuctioneerId() {
		return auctioneerId;
	}
	public void setAuctioneerId(int auctioneerId) {
		this.auctioneerId = auctioneerId;
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
	public List<Auction> getAuctions() {
		return auctions;
	}
	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}
	

}
