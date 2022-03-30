package com.simformsolutions.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simformsolutions.auction.model.Lot;

public interface LotRepository extends JpaRepository<Lot, Integer> {

}
