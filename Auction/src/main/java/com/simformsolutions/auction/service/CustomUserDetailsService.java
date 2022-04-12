package com.simformsolutions.auction.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.simformsolutions.auction.model.Admin;
import com.simformsolutions.auction.model.Auctioneer;
import com.simformsolutions.auction.model.Bidder;
import com.simformsolutions.auction.repository.AdminRepository;
import com.simformsolutions.auction.repository.AuctioneerRepository;
import com.simformsolutions.auction.repository.BidderRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	BidderRepository bidderRepository;
	
	@Autowired
	AuctioneerRepository auctioneerRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Auctioneer auctioneer = auctioneerRepository.findByemail(email);
		Bidder bidder=bidderRepository.findByemail(email);
		Admin admin = adminRepository.findByemail(email);
		
		if(auctioneer == null) {
			if(bidder != null) {
				return new org.springframework.security.core.userdetails.User(bidder.getEmail(), bidder.getPassword(),
						new ArrayList<>());
			}
			else{
				return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(),
						new ArrayList<>());
			}
		}
		return new org.springframework.security.core.userdetails.User(auctioneer.getEmail(), auctioneer.getPassword(),
				new ArrayList<>());
	}

}
