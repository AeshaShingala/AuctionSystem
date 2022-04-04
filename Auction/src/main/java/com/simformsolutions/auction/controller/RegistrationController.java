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

import com.simformsolutions.auction.model.AuctionHouse;
import com.simformsolutions.auction.model.Auctioneer;
import com.simformsolutions.auction.model.Bidder;
import com.simformsolutions.auction.repository.AuctionHouseRepository;
import com.simformsolutions.auction.repository.AuctioneerRepository;
import com.simformsolutions.auction.repository.BidderRepository;

@Controller
public class RegistrationController {
	
	@Autowired
	AuctionHouseRepository auctionHouseRepository ;
	@Autowired
	BidderRepository bidderRepository;
	@Autowired
	AuctioneerRepository auctioneerRepository;
	
	//Location For Storing Images
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/auctionHouseImage";

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(2000000);
		return multipartResolver;
	}
	
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
	@RequestMapping("/auctionhouse/register")
	public String auctionHouseRegister()
	{
		return "auctionHouseRegistration";
	}
	
	//adding new auction house
	@RequestMapping(value= "/auctionhouse/data",method = RequestMethod.POST)
	public ModelAndView auctionHouse(@ModelAttribute AuctionHouse auctionHouse,@RequestParam("imagee") MultipartFile file) {
		
		String fileName = file.getOriginalFilename();
		auctionHouse.setImage(fileName);
		Path path = Paths.get(uploadDirectory,fileName);
		try {
			Files.write(path, file.getBytes());
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		auctionHouseRepository.save(auctionHouse);
		return new ModelAndView("auctionHouses").addObject("auctionHouse", auctionHouse);
	}
	
	//Displays All AuctionHouses
	@RequestMapping("/auctionhouses")
	public ModelAndView auctionHouses() {
		
		List<AuctionHouse> listOfAuctionHouses = auctionHouseRepository.findAll();
		System.out.println(listOfAuctionHouses.get(0).getName());
		return new ModelAndView("auctionHouses").addObject("listOfAuctionHouses", listOfAuctionHouses);
	}
	
	
}
