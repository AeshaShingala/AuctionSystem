package com.simformsolutions.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simformsolutions.auction.model.Auctioneer;

public interface AuctioneerRepository extends JpaRepository<Auctioneer, Integer> {

	boolean existsByemail(String email);

}
