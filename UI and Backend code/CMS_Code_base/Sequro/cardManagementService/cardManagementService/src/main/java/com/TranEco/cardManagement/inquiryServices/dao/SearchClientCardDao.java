package com.TranEco.cardManagement.inquiryServices.dao;

import com.TranEco.cardManagement.inquiryServices.model.SearchClientCardRequest;
import com.TranEco.cardManagement.inquiryServices.model.SearchClientCardResponse;

public interface SearchClientCardDao {
	
	public SearchClientCardResponse searchClientCard(SearchClientCardRequest request);
}
