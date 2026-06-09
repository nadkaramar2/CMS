package com.traneco.report.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CardInventoryIssued implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String card_issue_date;
	private String cardType;
	private String sold;
	private String card_number;
	private String card_status;
	private String card_bin;
	private String expiry_date;
	

	@Override
	public String toString() {
		return "CardInventoryIssued [card_issue_date=" + card_issue_date + ", card_type=" + cardType + ", sold=" + sold
				+ "]";
	}

}
