package com.simformsolutions.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simformsolutions.auction.model.Auctioneer;
import com.simformsolutions.auction.model.UserToken;

public interface UserTokenRepository extends JpaRepository<UserToken, Integer> {

	UserToken findByToken(String token);

	void deleteByemail(String email);

	UserToken findByemail(String email);

}
