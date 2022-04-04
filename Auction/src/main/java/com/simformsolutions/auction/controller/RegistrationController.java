package com.simformsolutions.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.simformsolutions.auction.model.Auction;
import com.simformsolutions.auction.model.AuctionHouse;
import com.simformsolutions.auction.model.Auctioneer;
import com.simformsolutions.auction.model.Bidder;
import com.simformsolutions.auction.repository.AuctionHouseRepository;
import com.simformsolutions.auction.repository.AuctionRepository;
import com.simformsolutions.auction.repository.AuctioneerRepository;
import com.simformsolutions.auction.repository.BidderRepository;

@Controller
public class RegistrationController {
	
	@Autowired
	private AuctionHouseRepository auctionHouseRepository ;
	@Autowired
	private BidderRepository bidderRepository;
	@Autowired
	private AuctioneerRepository auctioneerRepository;
	@Autowired
	private AuctionRepository auctionRepository;

	//Home Page
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	//Bidder Register Page
	@RequestMapping(value= "/bidder/register",method = RequestMethod.GET)
	public ModelAndView bidderRegister() {
		String user = "bidder";
		return new ModelAndView("userRegistration").addObject("user",user);
	}
	
	//Bidder Data Handler
	@RequestMapping(value= "/bidder/data",method = RequestMethod.POST)
	@ResponseBody
	public String bidderData(@ModelAttribute Bidder bidder) {
		bidderRepository.save(bidder);
		return "<h1>You Are Registered as bidder</h1>";
	}
	
	//Auctioneer Register Page
	@RequestMapping(value= "/auctioneer/register",method = RequestMethod.GET)
	public ModelAndView auctioneerRegister() {
		String user = "auctioneer";
		return new ModelAndView("userRegistration").addObject("user",user);
	}
	
	//Auctioneer Data Handler
	@RequestMapping(value= "/auctioneer/data",method = RequestMethod.POST)
	@ResponseBody
	public String auctioneerData(@ModelAttribute Auctioneer auctioneer) {
		auctioneerRepository.save(auctioneer);
		return "<h1>You Are Registered as auctioneer</h1>";
	}
	
	// creating an auction house
	@RequestMapping("/auctionHouse/register")
	public String auctionHouseRegister()
	{
		return "auctionHouseRegistration";
	}
	
	//adding new auction house entry
	@RequestMapping(value= "/auctionHouse/data",method = RequestMethod.POST)
	@ResponseBody
	public String addAuctionHouse(@ModelAttribute AuctionHouse auctionHouse) {
		auctionHouseRepository.save(auctionHouse);
		return "<h1>Added</h1>";
	}

	//adding auction event, assign auctioneer to event
	@RequestMapping(value="/auction/register")
	public String auctionRegister()
	{
		return "auctionRegistration";
	}
	
	@RequestMapping(value="/auction/data",method=RequestMethod.POST)
	@ResponseBody
	public String addAuction(@ModelAttribute Auction auction)
	{
		
		auctionRepository.save(auction);
		return "Added";
	}
	
}
