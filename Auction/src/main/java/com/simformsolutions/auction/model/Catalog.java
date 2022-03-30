package com.simformsolutions.auction.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Catalog {

	@Id
	private int catalogId;
	private String category;

	@OneToMany(targetEntity = Lot.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fkCatalogId", referencedColumnName = "catalogId")
	private List<Lot> lots;

	@OneToOne(mappedBy = "catalog")
	private Auction auction;

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Lot> getLots() {
		return lots;
	}

	public void setLots(List<Lot> lots) {
		this.lots = lots;
	}

}
