package com.traneco.chargemodule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traneco.chargemodule.dao.AccountTypeChargesDao;
import com.traneco.configuration.model.AccountTypeCharges;

@Service
public class AccountTypeChargesServiceImpl implements AccountTypeChargesService
{
	
	@Autowired
	AccountTypeChargesDao accountTypeChargesDao;
	
	@Override
	public AccountTypeCharges getAccountTypeChargesBasedOnAccountType(AccountTypeCharges accountTypeCharges) {
		
		return accountTypeChargesDao.getAccountTypeChargesBasedOnAccountType(accountTypeCharges);
	}

	@Override
	public AccountTypeCharges getAccountTypeChargesBasedOnAccType(AccountTypeCharges accountTypeCharges) {
		
		return accountTypeChargesDao.getAccountTypeChargesBasedOnAccType(accountTypeCharges);
	}

}
