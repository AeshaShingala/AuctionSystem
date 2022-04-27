package com.simformsolutions.auction.controller;

import java.io.IOException;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import com.simformsolutions.auction.utility.AuctionUtility;
import com.simformsolutions.auction.utility.JwtUtil;
import com.simformsolutions.auction.model.AuthRequest;
import com.simformsolutions.auction.repository.AdminRepository;
import com.simformsolutions.auction.repository.AuctionRepository;
import com.simformsolutions.auction.repository.AuctioneerRepository;
import com.simformsolutions.auction.repository.BidderRepository;
import com.simformsolutions.auction.service.CustomUserDetailsService;

@Controller
public class LoginController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	BidderRepository bidderRepository;

	@Autowired
	AuctioneerRepository auctioneerRepository;

	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	AuctionRepository auctionRepository;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@RequestMapping("bidder/login")
	public String bidderLogin() {
		return "bidderLogin";
	}

	@RequestMapping("auctioneer/login")
	public String auctioneerLogin() {
		return "auctioneerLogin";
	}

	@RequestMapping("admin/login")
	public String adminLogin() {
		return "adminLogin";
	}

	@RequestMapping("bidder/login/data")
	public void bidderLoginData(@ModelAttribute AuthRequest authRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie cookie = AuctionUtility.cookieMaker(authRequest.getEmail(),authRequest.getPassword(),authenticationManager,jwtUtil,customUserDetailsService,request);
		if(cookie == null){
			response.sendRedirect("/bidder/login/invalid");			
		}	else {
			response.addCookie(cookie);
			System.out.println(cookie.getValue());
			response.sendRedirect("/auctions");
		}
	}

	@RequestMapping("auctioneer/login/data")
	public void auctioneerLoginData(@ModelAttribute AuthRequest authRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie cookie = AuctionUtility.cookieMaker(authRequest.getEmail(),authRequest.getPassword(),authenticationManager,jwtUtil,customUserDetailsService,request);
		if(cookie == null){
			response.sendRedirect("/auctioneer/login/invalid");
		}	else {
			response.addCookie(cookie);
			System.out.println(cookie.getValue());
			response.sendRedirect("/auction/register");
		}
	}

	@RequestMapping(value="admin/login/data",method = RequestMethod.POST )
	public void adminLoginData(@ModelAttribute AuthRequest authRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie cookie = AuctionUtility.cookieMaker(authRequest.getEmail(),authRequest.getPassword(),authenticationManager,jwtUtil,customUserDetailsService,request);
		if(cookie == null){
			response.sendRedirect("/admin/login/invalid"); 	
		}	else {
			System.out.println(cookie.getValue());
			response.addCookie(cookie);
			if(authRequest.getEmail().contains("proxibid"))
				response.sendRedirect("/admins");
			else
			response.sendRedirect("/admin");
		}
	}
	
	@RequestMapping(value = "{user}/login/invalid")
	public ModelAndView invalidLogin(@PathVariable("user") String user){
		String view = user+"Login";
		return new ModelAndView(view).addObject("invalid", true);
	}
	
	@RequestMapping("/access")
	public ModelAndView accessdenied() {
		return new ModelAndView("access");
	}
}
