package com.simformsolutions.auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.simformsolutions.auction.model.Admin;
import com.simformsolutions.auction.repository.AdminRepository;
import com.simformsolutions.auction.repository.AuctioneerRepository;
import com.simformsolutions.auction.repository.BidderRepository;

@Controller
public class AdminController {

	@Autowired
	private BidderRepository bidderRepository;

	@Autowired
	private AuctioneerRepository auctioneerRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
    PasswordEncoder passwordEncoder;

	// Register admin
	@RequestMapping(value = "/admin/register", method = RequestMethod.GET)
	public String adminRegister() {
		return "adminRegistration";
	}

	// Admin Data Handler
	@RequestMapping(value = "/admin/data", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView AdminData(@ModelAttribute Admin admin) {
		String email = admin.getEmail();
		if (auctioneerRepository.existsByemail(email) == true 
				|| adminRepository.existsByemail(email) == true || bidderRepository.existsByemail(email) == true) {
			return new ModelAndView("adminRegistration").addObject("exists", true);
		} else {
			String encodedPass = passwordEncoder.encode((admin.getPassword()));
			admin.setPassword(encodedPass);
			adminRepository.save(admin);
			return new ModelAndView("adminLogin");
		}
	}
	
	@GetMapping("/admin")
	public String adminDashboard() {
		return "adminDashboard";
	}
	
	@GetMapping("/admins")
	public ModelAndView showall() {
		ModelAndView mv = new ModelAndView("admins");
		List<Admin> listOfAdmins = adminRepository.findAll();
		listOfAdmins.remove(adminRepository.findByemail("mohit@proxibid.com"));
		mv.addObject("listOfAdmins",listOfAdmins);
		return mv;
	}
	
	@RequestMapping("/admins/active/{adminId}")
	public String active(@PathVariable("adminId") int id) {
		Admin dbAdmin = adminRepository.findById(id).orElse(null);
		dbAdmin.setActive(true);
		String redirectUrl = "/admins";
		adminRepository.save(dbAdmin);
		return "redirect:" + redirectUrl;	
	}
	
	@RequestMapping("/admins/deactive/{adminId}")
	public String deactive(@PathVariable("adminId") int id) {
		Admin dbAdmin = adminRepository.findById(id).orElse(null);
		dbAdmin.setActive(false);
		
		String redirectUrl = "/admins";
		adminRepository.save(dbAdmin);
		return "redirect:" + redirectUrl;	
	}
	
	@RequestMapping("/admins/delete/{adminId}")
	public String delete(@PathVariable("adminId") int id) {
		Admin dbAdmin = adminRepository.findById(id).orElse(null);
		adminRepository.delete(dbAdmin);
		String redirectUrl = "/admins";
		return "redirect:" + redirectUrl;	
	}
}
