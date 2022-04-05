package com.simformsolutions.auction.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
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
	private AuctionHouseRepository auctionHouseRepository;

	@Autowired
	private BidderRepository bidderRepository;

	@Autowired
	private AuctioneerRepository auctioneerRepository;

	@Autowired
	private AuctionRepository auctionRepository;

	// Location For Storing auction House Images
	public static String uploadHouseDirectory = System.getProperty("user.dir") + "/src/main/webapp/auctionHouseImage";
	
	// Location For Storing auction Images
	public static String uploadAuctionDirectory = System.getProperty("user.dir") + "/src/main/webapp/auctionImage";

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(2000000);
		return multipartResolver;
	}

	// Home Page
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	//form for creating an auction house
	@RequestMapping("/auctionhouse/register")
	public String auctionHouseRegister() {
		return "auctionHouseRegistration";
	}

	// saving new auction house
	@RequestMapping(value = "/auctionhouse/data", method = RequestMethod.POST)
	public ModelAndView auctionHouse(@ModelAttribute AuctionHouse auctionHouse,
			@RequestParam("imagee") MultipartFile file) {

		String fileName = file.getOriginalFilename();
		auctionHouse.setImage(fileName);
		Path path = Paths.get(uploadHouseDirectory, fileName);
		try {
			Files.write(path, file.getBytes());
		} catch (IOException e) {

			e.printStackTrace();
		}
		auctionHouseRepository.save(auctionHouse);
		return new ModelAndView("auctionHouses").addObject("auctionHouse", auctionHouse);
	}

	// Displays All AuctionHouses
	@RequestMapping("/auctionhouses")
	public ModelAndView displayAuctionHouses() {

		List<AuctionHouse> listOfAuctionHouses = auctionHouseRepository.findAll();
		return new ModelAndView("auctionHouses").addObject("listOfAuctionHouses", listOfAuctionHouses);
	}

	// Auctioneer Register Page
	@RequestMapping(value = "/auctioneer/register", method = RequestMethod.GET)
	public ModelAndView auctioneerRegister() {
		String user = "auctioneer";
		ModelAndView mv = new ModelAndView("userRegistration");
		mv.addObject("user", user);
		mv.addObject("listAuctionHouses", auctionHouseRepository.findAll());
		return mv;
	}

	// save Auctioneer Data
	@RequestMapping(value = "/auctioneer/data", method = RequestMethod.POST)
	@ResponseBody
	public String auctioneerData(@ModelAttribute Auctioneer auctioneer,
			@RequestParam(name = "selectedAuctionHouse") int auctionHouseId) {

		AuctionHouse auc = auctionHouseRepository.findById(auctionHouseId).orElse(null);
		auc.setAuctioneer(auctioneer);
		auctioneerRepository.save(auctioneer);
		return "<h1>You Are Registered as auctioneer</h1>";
	}

	// Displays All Auctioneers
	@RequestMapping("/auctioneers")
	public ModelAndView displayAuctioneers() {

		List<Auctioneer> listOfAuctioneers = auctioneerRepository.findAll();
		return new ModelAndView("showAuctioneers").addObject("listOfAuctioneers", listOfAuctioneers);
	}
	
	//form page to add auction event
	@RequestMapping("/auction/register")
	public String auctionRegister(@ModelAttribute Auction auction) {
		return "auctionRegistration";
	}

	//save auction event's data
//	@RequestMapping(value = "/auction/data", method = RequestMethod.POST)
//	@ResponseBody
//	public String auctionData(@ModelAttribute Auction auction) {
//		auctionRepository.save(auction);
//		return "Saved";
//	}
	
	@RequestMapping(value = "/auction/data", method = RequestMethod.POST)
	public ModelAndView auctionData(@ModelAttribute Auction auction,
			@RequestParam("imagee") MultipartFile file) {

		String fileName = file.getOriginalFilename();
		auction.setImage(fileName);
		Path path = Paths.get(uploadAuctionDirectory, fileName);
		try {
			Files.write(path, file.getBytes());
		} catch (IOException e) {

			e.printStackTrace();
		}
		auctionRepository.save(auction);
//		return new ModelAndView("auctionHouses").addObject("auctionHouse", auctionHouse);
		return new ModelAndView("home");
	}
               
	// Bidder Register Page
	@RequestMapping(value = "/bidder/register", method = RequestMethod.GET)
	public ModelAndView bidderRegister() {
		String user = "bidder";
		return new ModelAndView("userRegistration").addObject("user", user);
	}

	// Bidder Data Handler
	@RequestMapping(value = "/bidder/data", method = RequestMethod.POST)
	@ResponseBody
	public String bidderData(@ModelAttribute Bidder bidder) {
		bidderRepository.save(bidder);
		return "<h1>You are registered as bidder</h1>";
	}
}
