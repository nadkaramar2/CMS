package com.traneco.chargemodule.dao;

import com.traneco.configuration.model.AccountTypeCharges;

public interface AccountTypeChargesDao
{
		
	AccountTypeCharges getAccountTypeChargesBasedOnAccountType(AccountTypeCharges accountTypeCharges);

	AccountTypeCharges getAccountTypeChargesBasedOnAccType(AccountTypeCharges accountTypeCharges);

}
