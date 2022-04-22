package com.simformsolutions.auction.controller;

import java.security.Principal;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simformsolutions.auction.model.Auction;
import com.simformsolutions.auction.model.Auctioneer;
import com.simformsolutions.auction.model.Category;
import com.simformsolutions.auction.model.Lot;
import com.simformsolutions.auction.repository.AuctionRepository;
import com.simformsolutions.auction.repository.AuctioneerRepository;
import com.simformsolutions.auction.repository.CategoryRepository;

@Controller
public class AuctioneerController {

	@Autowired
	AuctioneerRepository auctioneerRepository;
	
	@Autowired
	AuctionRepository auctionRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/auctioneer/dashboard")
	public ModelAndView auctioneerDashboard(Principal principal) {
		Auctioneer auctioneer = auctioneerRepository.findByemail(principal.getName());
		return new ModelAndView("auctioneerDashboard").addObject("listOfAuctions", auctioneer.getAuctions());
	}
	
	@RequestMapping("editCatalog/{auctionId}")
	public ModelAndView editCatalog(@PathVariable("auctionId") int auctionId) {
		Auction auction = auctionRepository.findById(auctionId).orElse(null);
		List<Lot> lots = auction.getCatalog();
		Category cat=null;
		List<Category> categories = categoryRepository.findAll();
		for (Category category : categories) {
			if (category.getLots().contains(lots.get(0))) {
				cat = category;
			}
		}
		System.out.println(cat.getCategory());
		List<LocalTime> startLocalTimes = lots.stream().map(Lot::getStartTime).collect(Collectors.toList());
		List<String> startTimes = startLocalTimes.stream().map(LocalTime::toString).collect(Collectors.toList());
		
		List<LocalTime> endLocalTimes = lots.stream().map(Lot::getEndTime).collect(Collectors.toList());
		List<String> endTimes = endLocalTimes.stream().map(LocalTime::toString).collect(Collectors.toList());
		
		int sizeOfLot = lots.size();
		ModelAndView mv = new ModelAndView("editLots");
		return mv.addObject("auction", auction)
				.addObject("listCategories",categoryRepository.findAll())
				.addObject("listOfLots",lots)
				.addObject("cat", cat);
	}
	
}
