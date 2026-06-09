package com.TranEco.cardManagement.inquiryServices.dao;

import com.TranEco.cardManagement.inquiryServices.model.DetailedClientCardRequest;
import com.TranEco.cardManagement.inquiryServices.model.DetailedClientCardResponse;

public interface DetailedClientCardDao {
	
	public DetailedClientCardResponse DetailedClientCard(DetailedClientCardRequest request);
}
