package com.simformsolutions.auction.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Lot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lotId;
	private String title;
	private String description;
	private int quantity;
	private int basePrice;
	private String image;
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime startTime;
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime endTime;

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Lot() {

	}

	public Lot(String title, String description, int quantity, int basePrice, String image) {
		this.title = title;
		this.description = description;
		this.quantity = quantity;
		this.basePrice = basePrice;
		this.image = image;
	}

	public Lot(String title, String description, int quantity, int basePrice, String image, LocalTime startTime,
			LocalTime endTime) {
		super();
		this.title = title;
		this.description = description;
		this.quantity = quantity;
		this.basePrice = basePrice;
		this.image = image;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getLotId() {
		return lotId;
	}

	public void setLotId(int lotId) {
		this.lotId = lotId;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
