package com.traneco.chargemodule.services;

import com.traneco.configuration.model.AccountTypeCharges;


public interface AccountTypeChargesService {
	
	AccountTypeCharges getAccountTypeChargesBasedOnAccountType(AccountTypeCharges accountTypeCharges);

	AccountTypeCharges getAccountTypeChargesBasedOnAccType(AccountTypeCharges accountTypeCharges);
	
}
