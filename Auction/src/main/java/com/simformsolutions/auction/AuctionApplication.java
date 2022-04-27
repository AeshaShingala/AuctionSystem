package com.simformsolutions.auction;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.simformsolutions.auction.controller.RegistrationController;

@SpringBootApplication(scanBasePackages = { "com.simformsolutions"})
public class AuctionApplication {

	public static void main(String[] args) {
		new File(RegistrationController.uploadAuctionDirectory).mkdir();
		new File(RegistrationController.uploadHouseDirectory).mkdir();
		new File(RegistrationController.uploadLotDirectory).mkdir();
		SpringApplication.run(AuctionApplication.class, args);
	}

}
