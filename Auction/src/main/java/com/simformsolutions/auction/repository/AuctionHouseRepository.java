package com.simformsolutions.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simformsolutions.auction.model.AuctionHouse;

public interface AuctionHouseRepository extends JpaRepository<AuctionHouse, Integer> {

}
