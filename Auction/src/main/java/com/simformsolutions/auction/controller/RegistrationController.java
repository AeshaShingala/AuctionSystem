package com.simformsolutions.auction.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.simformsolutions.auction.model.Admin;
import com.simformsolutions.auction.model.Auction;
import com.simformsolutions.auction.model.AuctionHouse;
import com.simformsolutions.auction.model.Auctioneer;
import com.simformsolutions.auction.model.Bidder;
import com.simformsolutions.auction.model.UserReset;
import com.simformsolutions.auction.model.UserToken;
import com.simformsolutions.auction.repository.AdminRepository;
import com.simformsolutions.auction.repository.AuctionHouseRepository;
import com.simformsolutions.auction.repository.AuctionRepository;
import com.simformsolutions.auction.repository.AuctioneerRepository;
import com.simformsolutions.auction.repository.BidderRepository;
import com.simformsolutions.auction.repository.CategoryRepository;
import com.simformsolutions.auction.repository.UserTokenRepository;
import com.simformsolutions.auction.utility.AuctionUtility;
import com.simformsolutions.auction.utility.JwtUtil;

@Controller
public class RegistrationController {

	@Autowired
	private AuctionHouseRepository auctionHouseRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private BidderRepository bidderRepository;

	@Autowired
	private AuctioneerRepository auctioneerRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private AuctionRepository auctionRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserTokenRepository userTokenRepository;

	@Autowired
	private JwtUtil jwtUtil;

	// Location For Storing auction House Images
	public static String uploadHouseDirectory = System.getProperty("user.dir") + "/src/main/webapp/auctionHouseImage";

	// Location For Storing auction Images
	public static String uploadAuctionDirectory = System.getProperty("user.dir") + "/src/main/webapp/auctionImage";

	// Location For Storing lot Images
	public static String uploadLotDirectory = System.getProperty("user.dir") + "/src/main/webapp/lotsImage";

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(2000000);
		return multipartResolver;
	}

	// Home Page
	@RequestMapping("/")
	public ModelAndView home(Principal principal) {
		return new ModelAndView("home").addObject("listOfAuctions", auctionRepository.findAll()).addObject("isLoggedIn",principal != null);
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
		return "auctioneerRegistration";
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
	public ModelAndView auctioneerData(@ModelAttribute Auctioneer auctioneer,
			@RequestParam(name = "selectedAuctionHouse") int auctionHouseId, HttpServletResponse response) {
		String email = auctioneer.getEmail();
		if (auctioneerRepository.existsByemail(email) == true || adminRepository.existsByemail(email) == true
				|| bidderRepository.existsByemail(email) == true) {

			return new ModelAndView("auctioneerRegistration").addObject("exists", true).addObject("listAuctionHouses",
					auctionHouseRepository.findAll());

		} else {
			AuctionHouse auc = auctionHouseRepository.findById(auctionHouseId).orElse(null);
			String encodedPass = passwordEncoder.encode((auctioneer.getPassword()));
			auctioneer.setPassword(encodedPass);
			auc.setAuctioneer(auctioneer);
			auctionHouseRepository.save(auc);
			UserToken userToken = new UserToken(email, jwtUtil.generateToken(email));
			String generatedToken = userTokenRepository.save(userToken).getToken();

			// Generate Link With Register Token As Parameter And Email It To Auctioneer
			String link = "http://localhost:8080/check?resetToken=" + generatedToken;
			response.addHeader("resetToken", link);
			System.out.println(link);
			return new ModelAndView("auctioneers").addObject("listOfAuctioneers", auctioneerRepository.findAll());
		}
	}

	// Register Auction
	@RequestMapping(value = "/auction/register", method = RequestMethod.GET)
	public ModelAndView auctionRegister(@ModelAttribute Auction auction,Principal principal ) {
		Auctioneer auctioneer = auctioneerRepository.findByemail(principal.getName());
		ModelAndView mv = new ModelAndView("auctionRegistration").addObject("auctioneer",auctioneer);
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
//		System.out.println(categoryRepository.findAll());
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
	public ModelAndView bidderData(@ModelAttribute Bidder bidder) {
		String email = bidder.getEmail();
		if (auctioneerRepository.existsByemail(email) || adminRepository.existsByemail(email)
				|| bidderRepository.existsByemail(email)) {
			return new ModelAndView("bidderRegistration").addObject("exists", true);
		} else {
			String encodedPass = passwordEncoder.encode((bidder.getPassword()));
			bidder.setPassword(encodedPass);
			bidderRepository.save(bidder);
			return new ModelAndView("bidderLogin");
		}
	}

	@RequestMapping("/check")
	public ModelAndView checkToken(@RequestParam("resetToken") String token) {
		String tokenEmail = jwtUtil.extractUsername(token);
		String dbEmail = userTokenRepository.findByToken(token).getEmail();
		if(dbEmail == null || dbEmail.equals("") || tokenEmail == null || tokenEmail.equals(""))
			return new ModelAndView("invalidToken");
		if (dbEmail.equals(tokenEmail)) {
			ModelAndView mv = new ModelAndView("resetPassword").addObject("email", tokenEmail);
			mv.addObject("tokenEmail", tokenEmail);
			return mv;
		} else
			return new ModelAndView("invalidToken");
	}

	@RequestMapping("/testingResetPassword")
	public String TestingReset() {
		return "resetPassword";
	}

	@PostMapping("/reset")
	@ResponseBody
	public String resetPassword(@ModelAttribute UserReset userReset)
	{
		String email = userReset.getEmail();
		String password = userReset.getPassword();
		boolean isAuctioneer = auctioneerRepository.existsByemail(email);
		boolean isBidder = bidderRepository.existsByemail(email);
		boolean isAdmin = adminRepository.existsByemail(email);
		if(isAuctioneer)
		{
			Auctioneer auctioneer = auctioneerRepository.findByemail(email);
			auctioneer.setPassword(passwordEncoder.encode(password));
			auctioneerRepository.save(auctioneer);
		}
		else if(isAdmin)
		{
			Admin admin = adminRepository.findByemail(email);
			admin.setPassword(passwordEncoder.encode(password));
			adminRepository.save(admin);
		}
		else {
			Bidder bidder = bidderRepository.findByemail(email);
			bidder.setPassword(passwordEncoder.encode(password));
			bidderRepository.save(bidder);
		}
		userTokenRepository.delete(userTokenRepository.findByemail(email));
		return "<h1>Password Reset SuccessFully</h1>";
	}
}
