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
import com.simformsolutions.auction.repository.AuctionHouseRepository;
import com.simformsolutions.auction.repository.AuctionRepository;
import com.simformsolutions.auction.repository.AuctioneerRepository;
import com.simformsolutions.auction.repository.BidderRepository;
import com.simformsolutions.auction.repository.CategoryRepository;
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
	public ModelAndView home() {
		return new ModelAndView("home").addObject("listOfAuctions", auctionRepository.findAll());
	}

	// Register Auction House
	@RequestMapping("/auctionhouse/register")
	public String auctionHouseRegister() {
		return "auctionHouseRegistration";
	}

	// Auction House Data Handler
	@RequestMapping(value = "/auctionhouse/data", method = RequestMethod.POST)
	public String auctionHouseData(@ModelAttribute AuctionHouse auctionHouse,
			@RequestParam("imagee") MultipartFile file) {

		String fileName = AuctionUtility.saveImage(uploadHouseDirectory, file);
		auctionHouse.setImage(fileName);
		auctionHouseRepository.save(auctionHouse);
		return "auctionHouses";
	}

	// Register Auctioneer
	@RequestMapping(value = "/auctioneer/register", method = RequestMethod.GET)
	public ModelAndView auctioneerRegister() {
		ModelAndView mv = new ModelAndView("auctioneerRegistration");
		mv.addObject("listAuctionHouses", auctionHouseRepository.findAll());
		return mv;
	}

	// Auctioneer Data Handler
	@RequestMapping(value = "/auctioneer/data", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView auctioneerData(@ModelAttribute Auctioneer auctioneer,
			@RequestParam(name = "selectedAuctionHouse") int auctionHouseId) {

		if (auctioneerRepository.existsByemail(auctioneer.getEmail()) == true) {
			return new ModelAndView("auctioneerRegistration").addObject("exists", true).addObject("listAuctionHouses",
					auctionHouseRepository.findAll());

		} else {
			AuctionHouse auc = auctionHouseRepository.findById(auctionHouseId).orElse(null);
			auc.setAuctioneer(auctioneer);
			auctionHouseRepository.save(auc);
			return new ModelAndView("auctioneers").addObject("listOfAuctioneers", auctioneerRepository.findAll());
		}
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
		System.out.println(categoryRepository.findAll());
		return new ModelAndView("lotsRegistration").addObject("auction", auctionRepository.save(auction))
				.addObject("listCategories", categoryRepository.findAll());
	}

	// Register Bidder
	@RequestMapping(value = "/bidder/register", method = RequestMethod.GET)
	public ModelAndView bidderRegister() {
		return new ModelAndView("bidderRegistration");
	}

	// Bidder Data Handler
	@RequestMapping(value = "/bidder/data", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView bidderData(@ModelAttribute Bidder bidder) {
		if (bidderRepository.existsByemail(bidder.getEmail()) == true) {
			return new ModelAndView("bidderRegistration").addObject("exists", true);
		} else {
			bidderRepository.save(bidder);
			return new ModelAndView("auctions");
		}
	}
}
