package com.TranEco.cardManagement.scheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SchedulerModel {
	
	@JsonProperty("strDemondCronExpr")
	private String cron;

	@JsonProperty("strCardType")
	private String cardType;
	
	private String id;
	
	@JsonProperty("strLength")
	private int length;
	
	private int strDemandFlag;
	
	private String cardNo;
	
	private String outTokenCard;
	
	private String encryptedCardNumber;
}
