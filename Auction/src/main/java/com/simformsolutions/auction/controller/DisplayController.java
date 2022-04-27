package com.simformsolutions.auction.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simformsolutions.auction.model.Auction;
import com.simformsolutions.auction.model.AuctionHouse;
import com.simformsolutions.auction.model.Auctioneer;
import com.simformsolutions.auction.repository.AuctionHouseRepository;
import com.simformsolutions.auction.repository.AuctionRepository;

@Controller
public class DisplayController {
	@Autowired
	private AuctionHouseRepository auctionHouseRepository;

	@Autowired
	private AuctionRepository auctionRepository;

	// Displays All AuctionHouses
	@RequestMapping("/auctionhouses")
	public ModelAndView displayAuctionHouses() {
		List<AuctionHouse> listOfAuctionHouses = auctionHouseRepository.findAll();
		return new ModelAndView("auctionHouses").addObject("listOfAuctionHouses", listOfAuctionHouses);
	}

	// Redirect to auction of a particular auction house
	@RequestMapping("/showAuctions/{id}")
	public ModelAndView showAuctions(@PathVariable("id") int auctionHouseId) {
		ModelAndView mv = new ModelAndView("auctions");
		AuctionHouse auctionhouse = auctionHouseRepository.findById(auctionHouseId).orElse(null);
		List<Auctioneer> auctioneers = auctionhouse.getAuctioneer();
		List<Auction> auctions = new ArrayList<Auction>();

		for (Auctioneer auctioneer : auctioneers) {
			auctions.addAll(auctioneer.getAuctions());
		}

		mv.addObject("listOfAuctions", auctions);
		return mv;
	}

	// Displays All Auction
	@RequestMapping("/auctions")
	public ModelAndView displayAuctions() {
		List<Auction> listOfAuctions = auctionRepository.findAll();
		return new ModelAndView("auctions").addObject("listOfAuctions", listOfAuctions);
	}

	// Redirect to catalog of a particular auction
	@RequestMapping("/showCatalog/{auctionId}")
	public ModelAndView showCatalog(@PathVariable("auctionId") int auctionId) {
		ModelAndView mv = new ModelAndView("catalog");
		Auction auc = auctionRepository.findById(auctionId).orElse(null);
		mv.addObject("listOfLots", auc.getCatalog());
		return mv;
	}

//	// Displays All Lots/catalog
//	@RequestMapping("/allLots")
//	public ModelAndView displayLots() {
//		return new ModelAndView("catalog").addObject("listOfLots", lotRepository.findAll());
//	}
}
