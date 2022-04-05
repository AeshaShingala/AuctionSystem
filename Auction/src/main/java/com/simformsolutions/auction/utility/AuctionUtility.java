package com.simformsolutions.auction.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.simformsolutions.auction.model.AuctionHouse;

public class AuctionUtility {
	public static String saveImage(String folderLocation ,MultipartFile file) {
		String fileName = file.getOriginalFilename();
		Path path = Paths.get(folderLocation, fileName);
		try {
			Files.write(path, file.getBytes());
		} catch (IOException e) {

			e.printStackTrace();
		}
		return fileName;
	}
//	
//	@RequestMapping(value = "/auctionhouse/data", method = RequestMethod.POST)
//	public ModelAndView auctionHouse(@ModelAttribute AuctionHouse auctionHouse,
//			@RequestParam("imagee") MultipartFile file) {
//
//		String fileName = file.getOriginalFilename();
//		auctionHouse.setImage(fileName);
//		Path path = Paths.get(uploadHouseDirectory, fileName);
//		try {
//			Files.write(path, file.getBytes());
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//		auctionHouseRepository.save(auctionHouse);
//		return new ModelAndView("auctionHouses").addObject("auctionHouse", auctionHouse);
//	}
}
