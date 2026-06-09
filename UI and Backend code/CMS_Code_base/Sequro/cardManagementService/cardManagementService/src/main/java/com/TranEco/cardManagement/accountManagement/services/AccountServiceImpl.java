package com.TranEco.cardManagement.accountManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.accountManagement.dao.AccountDao;
import com.TranEco.cardManagement.accountManagement.model.AccountRequest;
import com.TranEco.cardManagement.accountManagement.model.AccountResponse;
import com.TranEco.cardManagement.common.Constant;
import com.TranEco.cardManagement.services.EhCacheServiceImpl;

@Service
public class AccountServiceImpl implements AccountService 
{
	@Autowired
	EhCacheServiceImpl ehCacheService;

	@Autowired
	AccountDao accountDao;

	// currency code,branch

	@Override
	public AccountResponse accountManagement(AccountRequest request) 
	{
		AccountResponse response = new AccountResponse();
		if (validateAccountType(request.getStrParticipantID() + request.getStrAccountType())) 
		{
			response.setOutResponseCode(Constant.CMS_Constants.INVALID_ACCOUNT_TYPE);
			response.setMessage("Invalid Account Type");
			return response;
		}
		else 
		{
			return accountDao.accountManagement(request);
		}
	}

	public boolean validateAccountType(String partID) 
	{
		return ehCacheService.getCfg_Account_Type().get(partID) == null ? true : false;
	}
}
