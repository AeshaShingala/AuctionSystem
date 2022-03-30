package com.simformsolutions.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simformsolutions.auction.model.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, Integer> {

}
