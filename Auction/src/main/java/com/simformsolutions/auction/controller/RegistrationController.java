package com.simformsolutions.auction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.simformsolutions.auction.model.Bidder;

@Controller
public class RegistrationController {

	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "<h1>Hello World</h1>";
	}
	
	@RequestMapping(value= "/bidder/register",method = RequestMethod.GET)
	public String bidderRegister() {
		return "userRegistration";
	}
	
	@RequestMapping(value= "/bidder/data")
	@ResponseBody
	public String bidderData(Bidder bidder) {
		System.out.println(bidder);
		return "<h1>HElllldwad</h1>";
	}
}
