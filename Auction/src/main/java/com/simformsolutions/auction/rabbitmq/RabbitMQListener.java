package com.simformsolutions.auction.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;

import com.simformsolutions.auction.controller.BiddingController;

@Controller
public class RabbitMQListener implements MessageListener {

//	public static String data;

	@Override
    public void onMessage(Message message) {
        BiddingController.messageFromListner = new String(message.getBody());
        try {
			JSONObject jsonObject= new JSONObject(BiddingController.messageFromListner);
			BiddingController.data = (jsonObject.getString("bid"));
			BiddingController.userName = jsonObject.getString("userName");
		} catch (Exception e) {
			e.printStackTrace();
		}

        System.out.println("message: " + BiddingController.messageFromListner);
//        ModelAndView mv = new ModelAndView("home");
//        mv.addObject("bidder", new String(message.getBody()));
    }
}
