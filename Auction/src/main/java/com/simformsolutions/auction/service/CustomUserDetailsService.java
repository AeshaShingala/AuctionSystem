package com.simformsolutions.auction.service;

import java.util.ArrayList;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

		//Getting Api Path and returning appropriate object
		String path = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
		String user = path.substring(22, path.indexOf("/login"));
		System.out.println(user);
		
		List<SimpleGrantedAuthority> array = null;
		
		if (user.equals("admin")) {
			Admin admin = adminRepository.findByemail(email);
			if(email.contains("proxibid")) {
				array = Arrays.asList(new SimpleGrantedAuthority("SA_ADMIN"));
				return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(),
						true,true,true,true,array);
			}
			array = Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
			return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(),
					admin.isActive(),true,true,true,array);
			
		} else if (user.equals("auctioneer")) {
			Auctioneer auctioneer = auctioneerRepository.findByemail(email);
			array = Arrays.asList(new SimpleGrantedAuthority("AUCTIONEER"));
			return new org.springframework.security.core.userdetails.User(auctioneer.getEmail(), auctioneer.getPassword(),
					array);
			
		} else {
			Bidder bidder = bidderRepository.findByemail(email);
			array = Arrays.asList(new SimpleGrantedAuthority("BIDDER"));
			return new org.springframework.security.core.userdetails.User(bidder.getEmail(), bidder.getPassword(),
					array);
			
		}
		
	}

}
