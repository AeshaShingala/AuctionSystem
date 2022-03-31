package com.simformsolutions.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.simformsolutions.auction.model.Auctioneer;
import com.simformsolutions.auction.model.Bidder;
import com.simformsolutions.auction.repository.AuctioneerRepository;
import com.simformsolutions.auction.repository.BidderRepository;

@Controller
public class RegistrationController {
	
	
	@Autowired
	BidderRepository bidderRepository;
	@Autowired
	AuctioneerRepository auctioneerRepository;

	//Home Page
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "<h1>Hello World</h1>";
	}
	
	//Bidder Register Page
	@RequestMapping(value= "/bidder/register",method = RequestMethod.GET)
	public ModelAndView bidderRegister() {
		String user = "bidder";
		return new ModelAndView("userRegistration").addObject("user",user);
	}
	
	//Auctioneer Register Page
	@RequestMapping(value= "/auctioneer/register",method = RequestMethod.GET)
	public ModelAndView auctioneerRegister() {
		String user = "auctioneer";
		return new ModelAndView("userRegistration").addObject("user",user);
	}
	
	//Bidder Data Handler
	@RequestMapping(value= "/bidder/data",method = RequestMethod.POST)
	@ResponseBody
	public String bidderData(@ModelAttribute Bidder bidder) {
		bidderRepository.save(bidder);
		return "<h1>You Are Registered as bidder</h1>";
	}
	
	//Auctioneer Data Handler
	@RequestMapping(value= "/auctioneer/data",method = RequestMethod.POST)
	@ResponseBody
	public String auctioneerData(@ModelAttribute Auctioneer auctioneer) {
		auctioneerRepository.save(auctioneer);
		return "<h1>You Are Registered as auctioneer</h1>";
	}
}
