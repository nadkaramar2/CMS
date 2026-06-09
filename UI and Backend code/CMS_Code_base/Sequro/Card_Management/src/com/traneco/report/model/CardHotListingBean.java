package com.traneco.report.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CardHotListingBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int participant_id;
	private String cardType;
	private String description;
	private String card_number;
	private String card_bin;
	private String hotlisted;
	private String card_issue_date;
	private String status_change_user;
	private String card_hot_listed_date;
	private String card_hot_listed_time;
	private String card_status_description;
	private String card_status_changed_date;
	private String card_status_changed_time;
	

	

}
