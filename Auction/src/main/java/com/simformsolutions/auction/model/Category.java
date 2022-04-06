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
public class Category {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private int categoryId;
	private String category;
	
	@OneToMany(targetEntity = Lot.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fkCategoryId", referencedColumnName = "categoryId")
	private List<Lot> lots;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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

	//change this to list
	public void setLots(Lot lots) {
		this.lots.add(lots);
	}
}
