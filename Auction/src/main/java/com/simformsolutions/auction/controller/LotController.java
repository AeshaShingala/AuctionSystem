package com.simformsolutions.auction.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	public static String uploadLotDirectory = System.getProperty("user.dir") + "/src/main/webapp/lotsImage";
	
	@Autowired
	private LotRepository lotRepository;
	
	@Autowired
	private AuctionRepository auctionRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	// Lot data handler
	@RequestMapping(value = "/lots/data", method = RequestMethod.POST)
	public void auctioneerData(@RequestParam("title") List<String> titles,
			@RequestParam("description") List<String>	descriptions,
			@RequestParam("quantity") List<Integer> quantities,
			@RequestParam("basePrice") List<Integer> basePrices,
			@RequestParam("imagee") List<MultipartFile> images,
			@RequestParam("auctionId") int catalogId,
			@RequestParam("selectedCategory") int categoryId,
			@RequestParam("startTime") List<String> startStringTimes,
			@RequestParam("endTime") List<String> endStringTimes, HttpServletResponse response) throws IOException {
		
		List<Lot> listOfLots = new ArrayList<Lot>();
		for(int i=0;i<titles.size();i++) {
			
			String title = titles.get(i);
			String description = descriptions.get(i);
			int quantity = quantities.get(i);
			int basePrice = basePrices.get(i);
			MultipartFile file = images.get(i);
			LocalTime startTime = LocalTime.parse(startStringTimes.get((i))); 
			LocalTime endTime = LocalTime.parse(endStringTimes.get(i)); 
			
			String fileName = AuctionUtility.saveImage(uploadLotDirectory,file);
			listOfLots.add(new Lot( title,description,quantity,basePrice,fileName,startTime,endTime));
			Category category = categoryRepository.findById(categoryId).orElse(null);
			category.setLots(listOfLots);
			Auction auction = auctionRepository.findById(catalogId).orElse(null);
			auction.setCatalog(listOfLots);
		}
		lotRepository.saveAll(listOfLots);
//		return new ModelAndView("showCatalog").addObject("listOfLots",lotRepository.findAll());
		String path = "/showCatalog/"+catalogId;
		response.sendRedirect(path);
	}
	
//	//Register multiple lots
//	@RequestMapping("lots/register")
//	public String tempLots(){
//		return "lotsRegistration";
//	}
	

//	// Register single lot
//	@RequestMapping("/lot/register")
//	public String lotRegister() {
//		return "lotRegistration";
//	}

}
