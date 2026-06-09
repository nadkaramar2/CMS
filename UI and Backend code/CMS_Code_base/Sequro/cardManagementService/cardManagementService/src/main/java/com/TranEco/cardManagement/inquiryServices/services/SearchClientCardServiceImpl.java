package com.TranEco.cardManagement.inquiryServices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.inquiryServices.dao.SearchClientCardDao;
import com.TranEco.cardManagement.inquiryServices.model.SearchClientCardRequest;
import com.TranEco.cardManagement.inquiryServices.model.SearchClientCardResponse;
import com.TranEco.cardManagement.services.EhCacheService;

@Service
public class SearchClientCardServiceImpl implements SearchClientCardService{

	@Autowired
	EhCacheService ehCacheService;
	
	@Autowired
	SearchClientCardDao searchClientCardDao;
	
	//currency code,branch
	
	@Override
	public SearchClientCardResponse searchClientCard(SearchClientCardRequest request) {
				return searchClientCardDao.searchClientCard(request);	
			}
}
