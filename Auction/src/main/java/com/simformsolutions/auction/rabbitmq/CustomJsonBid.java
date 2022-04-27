package com.simformsolutions.auction.rabbitmq;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomJsonBid(
		@JsonProperty("bid") int bid,
		@JsonProperty("increment") int increment,
		@JsonProperty("userName") String userName
		) implements Serializable {

}
