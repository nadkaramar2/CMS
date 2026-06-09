package com.traneco.service.model;

import lombok.Data;

@Data
public class FundomoAccountRequest {
	private String accountNumber;
	private String ncmcId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String emailId;
	private String language;
	private String cardNumber;
	private String mnoCode;
	private String channelCode;
	private String pin;
}
