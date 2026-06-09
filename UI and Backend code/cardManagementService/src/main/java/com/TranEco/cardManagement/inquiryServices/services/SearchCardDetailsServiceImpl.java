package com.TranEco.cardManagement.inquiryServices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.inquiryServices.dao.SearchCardDetailsDao;
import com.TranEco.cardManagement.inquiryServices.model.EnquireCardDetailsRequest;
import com.TranEco.cardManagement.inquiryServices.model.EnquireCardDetailsResponse;
import com.TranEco.cardManagement.services.EhCacheService;

@Service
public class SearchCardDetailsServiceImpl implements SearchCardDetailsService{

	@Autowired
	EhCacheService ehCacheService;
	
	@Autowired
	SearchCardDetailsDao searchCardDetailsDao;
	
	//currency code,branch
	
	@Override
	public EnquireCardDetailsResponse searchCardDetails(EnquireCardDetailsRequest request) {
		EnquireCardDetailsResponse response = new EnquireCardDetailsResponse();
			response = searchCardDetailsDao.SearchCardDetails(request);	
			return response;
			}
}
