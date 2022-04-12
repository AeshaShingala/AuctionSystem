package com.simformsolutions.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.simformsolutions.auction.model.Admin;
import com.simformsolutions.auction.repository.AdminRepository;

@Controller
public class AdminController {

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
		if (adminRepository.existsByemail(admin.getEmail()) == true) {
			return new ModelAndView("adminRegistration").addObject("exists", true);
		} else {
			String encodedPass = passwordEncoder.encode((admin.getPassword()));
			admin.setPassword(encodedPass);
			adminRepository.save(admin);
			return new ModelAndView("adminLogin");
		}
	}
}
