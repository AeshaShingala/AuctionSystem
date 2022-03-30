package com.simformsolutions.auction.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class AuctionHouse {

	@Id
	private int auctionHouseId;
	private String name;
	private int contact;
	
	@OneToMany(targetEntity = Auctioneer.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fkAuctionHouseId",referencedColumnName = "auctionHouseId")
	private List<Auctioneer> auctioneer;

	public int getAuctionHouseId() {
		return auctionHouseId;
	}

	public void setAuctionHouseId(int auctionHouseId) {
		this.auctionHouseId = auctionHouseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public List<Auctioneer> getAuctioneer() {
		return auctioneer;
	}

	public void setAuctioneer(List<Auctioneer> auctioneer) {
		this.auctioneer = auctioneer;
	}
	
}
