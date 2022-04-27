package com.simformsolutions.auction.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView auctioneerData(@RequestParam("title") List<String> titles,
			@RequestParam("description") List<String> descriptions, @RequestParam("quantity") List<Integer> quantities,
			@RequestParam("basePrice") List<Integer> basePrices, @RequestParam("imagee") List<MultipartFile> images,
			@RequestParam("auctionId") int catalogId, @RequestParam("selectedCategory") int categoryId,
			@RequestParam("startTime") List<String> startStringTimes,
			@RequestParam("endTime") List<String> endStringTimes) throws IOException {

		int flag = 0;
		List<Lot> listOfLots = new ArrayList<Lot>();

		for (int i = 0; i < titles.size(); i++) {
			MultipartFile file = images.get(i);
			LocalTime startTime = LocalTime.parse(startStringTimes.get((i)));
			LocalTime endTime = LocalTime.parse(endStringTimes.get(i));
			String fileName = AuctionUtility.saveImage(uploadLotDirectory, file);

			listOfLots.add(new Lot(titles.get(i), descriptions.get(i), quantities.get(i), basePrices.get(i), fileName, startTime, endTime));
			// compare time
			if (startTime.isBefore(auctionRepository.findById(catalogId).orElse(null).getStartTime()) || endTime.isAfter(auctionRepository.findById(catalogId).orElse(null).getEndTime()))
				flag = 1;
		}
		
		Category category = categoryRepository.findById(categoryId).orElse(null);
		category.setLots(listOfLots);
		Auction auction = auctionRepository.findById(catalogId).orElse(null);
		auction.setCatalog(listOfLots);
		lotRepository.saveAll(listOfLots);

		if (flag == 1) {
			return new ModelAndView("editCatalog")
					.addObject("auction", auctionRepository.findById(catalogId).orElse(null))
					.addObject("listCategories", categoryRepository.findAll())
					.addObject("listOfLots", (auctionRepository.findById(catalogId).orElse(null)).getCatalog())
					.addObject("cat", categoryRepository.findById(categoryId).orElse(null));
		} else
			return new ModelAndView("catalog").addObject("listOfLots", listOfLots);
	}

	// Edit Lot Handler
	@RequestMapping(value = "/editcatalog/data", method = RequestMethod.POST)
	public ModelAndView editCatalogData(@RequestParam("lotId") List<Integer> lotIds,
			@RequestParam("title") List<String> titles, @RequestParam("description") List<String> descriptions,
			@RequestParam("quantity") List<Integer> quantities, @RequestParam("basePrice") List<Integer> basePrices,
			@RequestParam("imagee") List<MultipartFile> images, @RequestParam("auctionId") int catalogId,
			@RequestParam("selectedCategory") int categoryId, @RequestParam("startTime") List<String> startStringTimes,
			@RequestParam("endTime") List<String> endStringTimes) throws IOException {

		int flag=0;
		Lot lot;
		List<Lot> newLots = new ArrayList<Lot>();

		for (int i = 0; i < lotIds.size(); i++) {
			lot = lotRepository.findById(lotIds.get(i)).orElse(null);
			lot.setTitle(titles.get(i));
			lot.setBasePrice(basePrices.get(i));
			lot.setDescription(descriptions.get(i));
			lot.setQuantity(quantities.get(i));
			lot.setStartTime(LocalTime.parse(startStringTimes.get((i))));
			lot.setEndTime(LocalTime.parse(endStringTimes.get(i)));

			if (images.get(i).isEmpty()) {
				lot.setImage(lot.getImage());
			} else {
				MultipartFile file = images.get(i);
				String fileName = AuctionUtility.saveImage(uploadLotDirectory, file);
				lot.setImage(fileName);
			}
			
			if (lot.getStartTime().isBefore(auctionRepository.findById(catalogId).orElse(null).getStartTime()) || lot.getEndTime().isAfter(auctionRepository.findById(catalogId).orElse(null).getEndTime()))
				flag = 1;
			newLots.add(lot);
		}

		for (int i = lotIds.size(); i < titles.size(); i++) {
			MultipartFile file = images.get(i);
			LocalTime startTime = LocalTime.parse(startStringTimes.get((i)));
			LocalTime endTime = LocalTime.parse(endStringTimes.get(i));
			String fileName = AuctionUtility.saveImage(uploadLotDirectory, file);

			newLots.add(new Lot(titles.get(i), descriptions.get(i), quantities.get(i), basePrices.get(i), fileName,
					startTime, endTime));
		}
		Category category = categoryRepository.findById(categoryId).orElse(null);
		category.setLots(newLots);
		Auction auction = auctionRepository.findById(catalogId).orElse(null);
		auction.setCatalog(newLots);
		lotRepository.saveAll(newLots);
		
		if (flag == 1) {
			return new ModelAndView("editCatalog")
					.addObject("auction", auctionRepository.findById(catalogId).orElse(null))
					.addObject("listCategories", categoryRepository.findAll())
					.addObject("listOfLots", (auctionRepository.findById(catalogId).orElse(null)).getCatalog())
					.addObject("cat", categoryRepository.findById(categoryId).orElse(null));
		} else
			return new ModelAndView("catalog").addObject("listOfLots", newLots);
	}
}