package com.simformsolutions.auction.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Auction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int auctionId;
	private String title;
	private String description;
	private String image;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime startTime;
	private long duration;
	

	@OneToMany(targetEntity = Bidder.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fkAuctionId", referencedColumnName = "auctionId")
	private List<Bidder> bidder;
	
	@OneToMany(targetEntity = Lot.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fkCatalogId", referencedColumnName = "auctionId")
	private List<Lot> catalog;

	public int getAuctionId() {
		return auctionId;
	}

	public List<Lot> getCatalog() {
		return catalog;
	}

	//change this to list of lots
	public void setCatalog(Lot catalog) {
		this.catalog.add(catalog);
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public List<Bidder> getBidder() {
		return bidder;
	}

	public void setBidder(List<Bidder> bidder) {
		this.bidder = bidder;
	}

}
