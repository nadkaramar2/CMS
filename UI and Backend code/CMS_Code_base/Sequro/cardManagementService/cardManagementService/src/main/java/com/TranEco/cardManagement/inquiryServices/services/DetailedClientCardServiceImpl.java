package com.TranEco.cardManagement.inquiryServices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.inquiryServices.dao.DetailedClientCardDao;
import com.TranEco.cardManagement.inquiryServices.model.DetailedClientCardRequest;
import com.TranEco.cardManagement.inquiryServices.model.DetailedClientCardResponse;
import com.TranEco.cardManagement.services.EhCacheService;

@Service
public class DetailedClientCardServiceImpl implements DetailedClientCardService{

	@Autowired
	EhCacheService ehCacheService;
	
	@Autowired
	DetailedClientCardDao DetailedClientCardDao;
	
	//currency code,branch
	
	@Override
	public DetailedClientCardResponse detailedClientCard(DetailedClientCardRequest request) {
		DetailedClientCardResponse response = new DetailedClientCardResponse();
			response = DetailedClientCardDao.DetailedClientCard(request);
			return response;
			}
}
