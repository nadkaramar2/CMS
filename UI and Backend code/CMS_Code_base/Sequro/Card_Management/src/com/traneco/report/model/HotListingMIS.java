package com.traneco.report.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class HotListingMIS implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cardType;
	private String cardCount;
	private String network_type;
	private String network_count;
	private String total_hot_listed_count;
	/*
	 * private String card_number; private String status_change_date;
	 */
	@Override
	public String toString() {
		return "HotListingMIS [cardType=" + cardType + ", cardCount=" + cardCount + ", network_type=" + network_type
				+ ", network_count=" + network_count + ", total_hot_listed_count=" + total_hot_listed_count + "]";
	}
	
	
	
	
	
	
	
	

}
