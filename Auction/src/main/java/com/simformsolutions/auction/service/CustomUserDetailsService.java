package com.simformsolutions.auction.service;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

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

		// Getting Api Path and returning appropriate object
		String path = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
		String user="";
		System.out.println(path);
		System.out.println(user);
		String[] parts = path.split("/");
		if (parts.length > 3 ) {
			user = parts[3];
		}
		System.out.println(user);
		
		boolean isAuctioneer = auctioneerRepository.existsByemail(email);
		boolean isBidder = bidderRepository.existsByemail(email);
		boolean isAdmin = adminRepository.existsByemail(email);
		
		if((isAdmin || isAuctioneer || isBidder) == true) {
			
		List<SimpleGrantedAuthority> array = null;

		if (user.equals("admin") && isAdmin) {
			Admin admin = adminRepository.findByemail(email);
			if (email.contains("proxibid")) {
				array = Arrays.asList(new SimpleGrantedAuthority("SA_ADMIN"));
				return new User(admin.getEmail(), admin.getPassword(), true, true, true, true, array);
			}
			array = Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
			return new User(admin.getEmail(), admin.getPassword(), admin.isActive(), true, true, true, array);
		}
		if (user.equals("auctioneer") && isAuctioneer) {
			Auctioneer auctioneer = auctioneerRepository.findByemail(email);
			if (email.contains("proxibid")) {
				array = Arrays.asList(new SimpleGrantedAuthority("AUCTIONEER"));
				return new User(auctioneer.getEmail(), auctioneer.getPassword(), true, true, true, true, array);
			}
		}	
		if (user.equals("bidder") && isBidder) {
			Bidder bidder = bidderRepository.findByemail(email);
			if (email.contains("proxibid")) {
				array = Arrays.asList(new SimpleGrantedAuthority("BIDDER"));
				return new User(bidder.getEmail(), bidder.getPassword(), true, true, true, true, array);
			}
		}
		
		if (!user.equals("auctioneer") && !user.equals("bidder") && adminRepository.existsByemail(email)) {
			Admin admin = adminRepository.findByemail(email);
			if (email.contains("proxibid")) {
				array = Arrays.asList(new SimpleGrantedAuthority("SA_ADMIN"));
				return new User(admin.getEmail(), admin.getPassword(), true, true, true, true, array);
			}
			array = Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
			return new User(admin.getEmail(), admin.getPassword(), admin.isActive(), true, true, true, array);

		} else if (!user.equals("admin") && !user.equals("bidder") && auctioneerRepository.existsByemail(email)) {
			Auctioneer auctioneer = auctioneerRepository.findByemail(email);
			array = Arrays.asList(new SimpleGrantedAuthority("AUCTIONEER"));
			return new User(auctioneer.getEmail(), auctioneer.getPassword(), array);

		} else if (!user.equals("admin") && !user.equals("auctioneer") && bidderRepository.existsByemail(email)) {
			Bidder bidder = bidderRepository.findByemail(email);
			array = Arrays.asList(new SimpleGrantedAuthority("BIDDER"));
			return new User(bidder.getEmail(), bidder.getPassword(), array);

		}
		else {
			throw new UsernameNotFoundException(email + " Not Found: ");
		}
		}
		
		throw new UsernameNotFoundException(email + " Not Found: ");

	}

}
