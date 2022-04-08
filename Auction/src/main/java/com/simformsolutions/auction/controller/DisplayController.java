package com.simformsolutions.auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simformsolutions.auction.model.Auction;
import com.simformsolutions.auction.model.AuctionHouse;
import com.simformsolutions.auction.model.Auctioneer;
import com.simformsolutions.auction.model.Lot;
import com.simformsolutions.auction.repository.AuctionHouseRepository;
import com.simformsolutions.auction.repository.AuctionRepository;
import com.simformsolutions.auction.repository.AuctioneerRepository;
import com.simformsolutions.auction.repository.LotRepository;

@Controller
public class DisplayController {
	@Autowired
	private AuctionHouseRepository auctionHouseRepository;

//	@Autowired
//	private BidderRepository bidderRepository;

	@Autowired
	private AuctioneerRepository auctioneerRepository;

	@Autowired
	private AuctionRepository auctionRepository;

	@Autowired
	private LotRepository lotRepository;

//	@Autowired
//	private CategoryRepository categoryRepository;

	// Displays All AuctionHouses
	@RequestMapping("/auctionhouses")
	public ModelAndView displayAuctionHouses() {
		List<AuctionHouse> listOfAuctionHouses = auctionHouseRepository.findAll();
		return new ModelAndView("auctionHouses").addObject("listOfAuctionHouses", listOfAuctionHouses);
	}

	// Displays All Auctioneers
	@RequestMapping("/auctioneers")
	public ModelAndView displayAuctioneers() {
		List<Auctioneer> listOfAuctioneers = auctioneerRepository.findAll();
		return new ModelAndView("auctioneers").addObject("listOfAuctioneers", listOfAuctioneers);
	}

	// Displays All Auction
	@RequestMapping("/auctions")
	public ModelAndView displayAuctions() {
		List<Auction> listOfAuctions = auctionRepository.findAll();
		return new ModelAndView("auctions").addObject("listOfAuctions", listOfAuctions);
	}
	
	// Displays All Lots/catalog
	@RequestMapping("/lots")
	public ModelAndView displayLots() {
		List<Lot> listOfLots = lotRepository.findAll();
		return new ModelAndView("catalog").addObject("catalog", listOfLots);
	}

}
