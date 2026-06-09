package com.traneco.report.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CardInventoryPending implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String card_issue_date;
	private String card_bin;
	private String cardtype;
	private String card_number;
	private String unsold;
	private String expiry_date;
	private String card_status;
	
	
	
	
	
	
	
	
}
