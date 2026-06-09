package com.TranEco.cardManagement.inquiryServices.dao;

import com.TranEco.cardManagement.inquiryServices.model.EnquireCardDetailsRequest;
import com.TranEco.cardManagement.inquiryServices.model.EnquireCardDetailsResponse;

public interface SearchCardDetailsDao {
	
	public EnquireCardDetailsResponse SearchCardDetails(EnquireCardDetailsRequest request);
}
