package com.simformsolutions.auction.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;


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

}
