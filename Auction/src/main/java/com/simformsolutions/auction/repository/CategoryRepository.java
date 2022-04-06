package com.simformsolutions.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simformsolutions.auction.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
