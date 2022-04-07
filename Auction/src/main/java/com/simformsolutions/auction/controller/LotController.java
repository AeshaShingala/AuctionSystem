package com.simformsolutions.auction.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.simformsolutions.auction.model.Auction;
import com.simformsolutions.auction.model.Category;
import com.simformsolutions.auction.model.Lot;
import com.simformsolutions.auction.repository.AuctionRepository;
import com.simformsolutions.auction.repository.CategoryRepository;
import com.simformsolutions.auction.repository.LotRepository;
import com.simformsolutions.auction.utility.AuctionUtility;

@Controller
public class LotController {
	
	public static String uploadLotDirectory = System.getProperty("user.dir") + "/src/main/webapp/lots";
	
	@Autowired
	private LotRepository lotRepository;
	
	@Autowired
	private AuctionRepository auctionRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value = "/lots/data", method = RequestMethod.POST)
	@ResponseBody
	public String auctioneerData(@RequestParam("title") List<String> titles,
			@RequestParam("description") List<String>	descriptions,
			@RequestParam("quantity") List<Integer> quantities,
			@RequestParam("basePrice") List<Integer> basePrices,
			@RequestParam("imagee") List<MultipartFile> images,
			@RequestParam("auctionId") int catalogId,
			@RequestParam("selectedCategory") int categoryId) {

		List<Lot> listOfLots = new ArrayList<Lot>();
		for(int i=0;i<titles.size();i++) {
			
			String title = titles.get(i);
			String description = descriptions.get(i);
			int quantity = quantities.get(i);
			int basePrice = basePrices.get(i);
			MultipartFile file = images.get(i);
			String fileName = AuctionUtility.saveImage(uploadLotDirectory,file);
			listOfLots.add(new Lot( title,description,quantity,basePrice,fileName));
			Category category = categoryRepository.findById(categoryId).orElse(null);
			category.setLots(listOfLots);
			Auction auction = auctionRepository.findById(catalogId).orElse(null);
			auction.setCatalog(listOfLots);
		}
		lotRepository.saveAll(listOfLots);
		return "<h1>You Are Registered as auctioneer</h1>";
	}
	
	@RequestMapping("temp/lots")
	public String tempLots(){
		return "lotsRegistration";
	}
}
