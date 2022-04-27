package com.simformsolutions.auction.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.simformsolutions.auction.model.Bid;
import com.simformsolutions.auction.model.Lot;
import com.simformsolutions.auction.rabbitmq.CustomJsonBid;
import com.simformsolutions.auction.repository.LotRepository;

@Controller
public class BiddingController {
	
	
	@Autowired
	LotRepository lotRepository;
	
	@Autowired
	private RabbitTemplate template;
	
	CustomJsonBid message;
	
	public static String data="0";
	public static String messageFromListner="";
	public static String userName="";
	
	@GetMapping("/start/{lotId}")
    public ModelAndView getHome(@PathVariable("lotId") int lotId,Principal principal){
        Lot lot = lotRepository.findById(lotId).orElse(null);
        ModelAndView mv = new ModelAndView("bidding");
        mv.addObject("basePrice", lot.getBasePrice());
        mv.addObject("user",principal.getName().split("@")[0]);
        if(data =="0")
        	data= lot.getBasePrice() + "";
        return mv;
    }

    @RequestMapping(value = "/data",method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public CustomJsonBid getData(){
    	int intData = Integer.parseInt(data);
    	intData+=incrementFinder(intData);
        return new CustomJsonBid(Integer.parseInt(data),intData, userName);
    }

    //Publisher Code
    @RequestMapping(value="/bids",method = RequestMethod.POST)
    @ResponseBody
    public String setMessage(Bid bid,Principal principal) throws IOException, TimeoutException {
    	int intData = bid.getBid();
    	intData+=incrementFinder(intData);
    	String email = principal.getName().split("@")[0];
		userName = (String) email.subSequence(0, 3);
		for(int i =3 ; i <email.length();i++) {
			userName+="*";
		}
    	message = new CustomJsonBid(bid.getBid(),intData, userName);
        template.convertAndSend("message_exchange", "message_routing_key",message);
        return "success";
    }
    
    public static boolean isBetween(int current, int lower, int upper) {
    	  return lower <= current && current <= upper;
    	}
    
    public static int incrementFinder(int intData) {
    	if(isBetween(intData, 100, 199))
    	{
    		return 10;
    	}
    	else if(isBetween(intData, 20, 319))
    	{
    		return 20;
    	}
    	else if(isBetween(intData, 320, 499))
    	{
    		return 30;
    	}
    	else if(isBetween(intData, 500, 999))
    	{
    		return 50;
    	}
    	else if(isBetween(intData, 1000, 1999))
    	{
    		return 100;
    	}
    	else if(isBetween(intData, 2000, 3199))
    	{
    		return 200;
    	}
    	else if(isBetween(intData, 3200, 4999))
    	{
    		return 300;
    	}
    	else if(isBetween(intData, 5000, 9999))
    	{
    		return 500;
    	}
    	else {
    		return 1000;
    	}
    }
}
