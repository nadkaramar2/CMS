package com.TranEco.cardManagement.inquiryServices.services;

import com.TranEco.cardManagement.inquiryServices.model.DetailedClientCardRequest;
import com.TranEco.cardManagement.inquiryServices.model.DetailedClientCardResponse;

public interface DetailedClientCardService {
	public DetailedClientCardResponse detailedClientCard(DetailedClientCardRequest request);
}
