package com.simformsolutions.auction;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//import com.simformsolutions.auction.rabbitmq.RabbitMQListener;
import com.simformsolutions.auction.controller.RegistrationController;
import com.simformsolutions.auction.rabbitmq.RabbitMQListener;

@SpringBootApplication(scanBasePackages = { "com.simformsolutions","com.simformsolutions.auction.utility"})
public class AuctionApplication {

	public final String QUEUE = "message_queue";
	public final String EXCHANGE = "message_exchange";
	public final String ROUTINGKEY = "message_routing_key";

	@Bean
	Queue queue() {
		return new Queue(QUEUE);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(EXCHANGE);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(QUEUE);
		container.setMessageListener(new RabbitMQListener());
		return container;
	}

	/*@Bean
	MessageListenerAdapter listenerAdapter(Bidder receiver) {
		return new MessageListenerAdapter(receiver.toString(), "receiveMessage");
	}*/
	
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
	    final var rabbitTemplate = new RabbitTemplate(connectionFactory);
	    rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
	    return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
	    return new Jackson2JsonMessageConverter();
	}
	
	@PostConstruct
    private void postConstruct() {
		new File(RegistrationController.uploadAuctionDirectory).mkdir();
		new File(RegistrationController.uploadHouseDirectory).mkdir();
		new File(RegistrationController.uploadLotDirectory).mkdir();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AuctionApplication.class, args);
	}

}
