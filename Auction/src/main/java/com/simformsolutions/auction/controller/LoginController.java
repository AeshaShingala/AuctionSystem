package com.simformsolutions.auction.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.simformsolutions.auction.utility.AuctionUtility;
import com.simformsolutions.auction.utility.JwtUtil;
import com.simformsolutions.auction.model.AuthRequest;

@Controller
public class LoginController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

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
	public ModelAndView bidderLoginData(@ModelAttribute AuthRequest authRequest, HttpServletRequest request, HttpServletResponse response) {
		AuctionUtility au = new AuctionUtility();
		Cookie cookie = au.cookieMaker(authRequest.getEmail(),authRequest.getPassword(),authenticationManager,jwtUtil);
		if(cookie == null){
			return new ModelAndView("bidderLogin").addObject("invalid", true);			
		}	else {
			response.addCookie(cookie);
			System.out.println(cookie.getValue());
			return new ModelAndView("auctions");
		}
	}

	@RequestMapping("auctioneer/login/data")
	public ModelAndView auctioneerLoginData(@ModelAttribute AuthRequest authRequest, HttpServletRequest request, HttpServletResponse response) {
		AuctionUtility au = new AuctionUtility();
		Cookie cookie = au.cookieMaker(authRequest.getEmail(),authRequest.getPassword(),authenticationManager,jwtUtil);
		if(cookie == null){
			return new ModelAndView("auctioneerLogin").addObject("invalid", true);			
		}	else {
			response.addCookie(cookie);
			System.out.println(cookie.getValue());
			return new ModelAndView("auctions");
		}
	}

	@RequestMapping("admin/login/data")
	public ModelAndView adminLoginData(@ModelAttribute AuthRequest authRequest, HttpServletRequest request, HttpServletResponse response) {
		AuctionUtility au = new AuctionUtility();
		Cookie cookie = au.cookieMaker(authRequest.getEmail(),authRequest.getPassword(),authenticationManager,jwtUtil);
		if(cookie == null){
			return new ModelAndView("adminLogin").addObject("invalid", true);			
		}	else {
			response.addCookie(cookie);
			System.out.println(cookie.getValue());
			return new ModelAndView("auctions");
		}
	}

}
