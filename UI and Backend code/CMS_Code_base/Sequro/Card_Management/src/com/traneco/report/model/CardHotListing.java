package com.traneco.report.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CardHotListing implements Serializable{
	
	
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
	
	
	@Override
	public String toString() {
		return "CardHotListing [id=" + id + ", participant_id=" + participant_id + ", cardType=" + cardType
				+ ", description=" + description + ", card_number=" + card_number + ", card_bin=" + card_bin
				+ ", hotlisted=" + hotlisted + ", card_issue_date=" + card_issue_date + ", status_change_user="
				+ status_change_user + ", card_hot_listed_date=" + card_hot_listed_date + ", card_hot_listed_time="
				+ card_hot_listed_time + ", card_status_description=" + card_status_description
				+ ", card_status_changed_date=" + card_status_changed_date + ", card_status_changed_time="
				+ card_status_changed_time + "]";
	}
	
	
	

	
	
}
