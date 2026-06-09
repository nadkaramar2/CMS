package com.traneco.configuration.model;

import lombok.Data;

@Data
public class CardNoModel {
	private String userID;
	private String password;
	private String tokenCard;
	private String responseCode;
	private String responseDesc;
	private String cardNo;
	private String createdDate;
}
