package com.TranEco.cardManagement.accountManagement.dao;

import com.TranEco.cardManagement.accountManagement.model.AccountRequest;
import com.TranEco.cardManagement.accountManagement.model.AccountResponse;

public interface AccountDao {
	
	public AccountResponse accountManagement(AccountRequest request);
}
