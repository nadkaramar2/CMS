package com.TranEco.cardManagement.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.inquiryServices.model.CardDetails;
import com.TranEco.cardManagement.test.Dao.TestDao;

@Service
public class TestServiceImpl implements TestService
{
	@Autowired
	TestDao testDao;

	@Override
	public CardDetails getCardDetails(CardDetails request) 
	{
		return testDao.getCardDetails(request);
	}
	
}
