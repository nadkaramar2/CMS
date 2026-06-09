package com.traneco.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traneco.service.dao.CardChargesDao;
import com.traneco.service.model.CardAccountLinkage;

@Service
public class CardChargesServiceImpl implements CardChargesService
{

	@Autowired
	CardChargesDao cardChargesDao;
	
	@Override
	public int addCardCharges(CardAccountLinkage cardAccountLinkageObject) 
	{
		return cardChargesDao.addCardCharges(cardAccountLinkageObject);
		
	}

}
