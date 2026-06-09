package com.traneco.service.model;

import lombok.Data;

@Data
public class TransactionView {

	private String type;
	private String description;
	private String amount;
	private String refid;
	private String status;
	private String txndate;
	
	
}
