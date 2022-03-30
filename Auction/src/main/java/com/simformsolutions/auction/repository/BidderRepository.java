package com.simformsolutions.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simformsolutions.auction.model.Bidder;

public interface BidderRepository extends JpaRepository<Bidder, Integer> {

}
