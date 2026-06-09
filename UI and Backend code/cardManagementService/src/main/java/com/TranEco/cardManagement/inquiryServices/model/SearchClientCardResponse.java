package com.TranEco.cardManagement.inquiryServices.model;

import java.util.List;

import lombok.Data;

@Data
public class SearchClientCardResponse {
	private String outResponseCode;
	private String message;
	private List<SearchClientCardResponseList> searchClientCardResponseList;
	
}
