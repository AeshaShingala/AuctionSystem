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
public class AuctionHouse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int auctionHouseId;
	private String name;
	private long contact;
	private String image;
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

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

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public List<Auctioneer> getAuctioneer() {
		return auctioneer;
	}

	public void setAuctioneer(List<Auctioneer> auctioneer) {
		this.auctioneer = auctioneer;
	}
	
}
