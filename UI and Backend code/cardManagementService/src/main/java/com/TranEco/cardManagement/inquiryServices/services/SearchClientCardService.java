package com.TranEco.cardManagement.inquiryServices.services;

import com.TranEco.cardManagement.inquiryServices.model.SearchClientCardRequest;
import com.TranEco.cardManagement.inquiryServices.model.SearchClientCardResponse;

public interface SearchClientCardService {
	public SearchClientCardResponse searchClientCard(SearchClientCardRequest request);
}
