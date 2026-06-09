package com.TranEco.cardManagement.inquiryServices.services;

import com.TranEco.cardManagement.inquiryServices.model.EnquireCardDetailsRequest;
import com.TranEco.cardManagement.inquiryServices.model.EnquireCardDetailsResponse;


public interface SearchCardDetailsService {
	public EnquireCardDetailsResponse searchCardDetails(EnquireCardDetailsRequest request);
}
