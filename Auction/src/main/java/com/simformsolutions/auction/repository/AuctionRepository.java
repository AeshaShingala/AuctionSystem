package com.simformsolutions.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simformsolutions.auction.model.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Integer> {

}
