package com.traneco.report.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class TransactionBean implements Serializable{

	/*
	 * private Date fromDate; private Date toDate; private String cardType; private
	 * String networkScheme;
	 */
	private static final long serialVersionUID = 1L;
	private String transaction_time;
	private String card_number;
	private String transaction_amount;
	private String transaction_result;
	private String transaction_ref_no;
	private String mcc_code;
	private String network;
	
	
	
	
	
	@Override
	public String toString() {
		return "TransactionBean [transaction_time=" + transaction_time + ", card_number=" + card_number
				+ ", transaction_amount=" + transaction_amount + ", transaction_result=" + transaction_result
				+ ", transaction_ref_no=" + transaction_ref_no + ", mcc_code=" + mcc_code + ", network=" + network
				+ "]";
	}
	
	
}
