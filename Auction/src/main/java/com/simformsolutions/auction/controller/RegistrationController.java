package com.simformsolutions.auction.controller;

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
import com.simformsolutions.auction.model.Lot;
import com.simformsolutions.auction.model.Category;
import com.simformsolutions.auction.repository.AuctionHouseRepository;
import com.simformsolutions.auction.repository.AuctionRepository;
import com.simformsolutions.auction.repository.AuctioneerRepository;
import com.simformsolutions.auction.repository.BidderRepository;
import com.simformsolutions.auction.repository.CategoryRepository;
import com.simformsolutions.auction.repository.LotRepository;
import com.simformsolutions.auction.utility.AuctionUtility;

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

	@Autowired
	private LotRepository lotRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	// Location For Storing auction House Images
	public static String uploadHouseDirectory = System.getProperty("user.dir") + "/src/main/webapp/auctionHouseImage";

	// Location For Storing auction Images
	public static String uploadAuctionDirectory = System.getProperty("user.dir") + "/src/main/webapp/auctionImage";

	// Location For Storing lot Images
	public static String uploadLotDirectory = System.getProperty("user.dir") + "/src/main/webapp/lots";

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(2000000);
		return multipartResolver;
	}

	// Home Page
	@RequestMapping("/")
	public String home() {
		return "redirect:/auctions";
	}

	// Register Auction House
	@RequestMapping("/auctionhouse/register")
	public String auctionHouseRegister() {
		return "auctionHouseRegistration";
	}

	// Auction House Data Handler
	@RequestMapping(value = "/auctionhouse/data", method = RequestMethod.POST)
	public ModelAndView auctionHouseData(@ModelAttribute AuctionHouse auctionHouse,
			@RequestParam("imagee") MultipartFile file) {

		String fileName = AuctionUtility.saveImage(uploadHouseDirectory, file);
		auctionHouse.setImage(fileName);
		auctionHouseRepository.save(auctionHouse);
		return new ModelAndView("auctionHouses").addObject("auctionHouse", auctionHouse);
	}

	// Register Auctioneer
	@RequestMapping(value = "/auctioneer/register", method = RequestMethod.GET)
	public ModelAndView auctioneerRegister() {
		String user = "auctioneer";
		ModelAndView mv = new ModelAndView("userRegistration");
		mv.addObject("user", user);
		mv.addObject("listAuctionHouses", auctionHouseRepository.findAll());
		return mv;
	}

	// Auctioneer Data Handler
	@RequestMapping(value = "/auctioneer/data", method = RequestMethod.POST)
	@ResponseBody
	public String auctioneerData(@ModelAttribute Auctioneer auctioneer,
			@RequestParam(name = "selectedAuctionHouse") int auctionHouseId) {

		AuctionHouse auc = auctionHouseRepository.findById(auctionHouseId).orElse(null);
		auc.setAuctioneer(auctioneer);
		auctionHouseRepository.save(auc);
		return "<h1>You Are Registered as auctioneer</h1>";
	}

	// Register Auction
	@RequestMapping(value = "/auction/register", method = RequestMethod.GET)
	public ModelAndView auctionRegister(@ModelAttribute Auction auction) {
		ModelAndView mv = new ModelAndView("newAuctionRegistration");
		mv.addObject("listAuctioneers", auctioneerRepository.findAll());
		return mv;
	}

	// Auction Data Handler
	@RequestMapping(value = "/auction/data", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView auctionData(@ModelAttribute Auction auction, @RequestParam("imagee") MultipartFile file,
			@RequestParam(name = "selectedAuctioneer") int auctioneerId) {

		String fileName = AuctionUtility.saveImage(uploadAuctionDirectory, file);
		auction.setImage(fileName);
		Auctioneer auctioneer = auctioneerRepository.findById(auctioneerId).orElse(null);
		auctioneer.setAuctions(auction);
		return new ModelAndView("newLotRegistration").addObject("auction", auctionRepository.save(auction))
				.addObject("listCategories", categoryRepository.findAll());
	}

	// Register Lot
	@RequestMapping("/lot/register")
	public String lotRegister() {
		return "newlotRegistration";
	}

	// Lot Data Handler
	@RequestMapping(value = "/lot/data", method = RequestMethod.POST)
	@ResponseBody
	public String lotData(@ModelAttribute Lot lot, @RequestParam("auctionId") int catalogId,
			@RequestParam("imagee") MultipartFile file, @RequestParam("selectedCategory") int categoryId) {
		String fileName = AuctionUtility.saveImage(uploadLotDirectory, file);
		lot.setImage(fileName);
		Auction auction = auctionRepository.findById(catalogId).orElse(null);
		auction.setCatalog(lot);
		Category category = categoryRepository.findById(categoryId).orElse(null);
		category.setLots(lot);
		lotRepository.save(lot);
		return "<h1>Catalog Added</h1>";
	}

	// Register Bidder
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
