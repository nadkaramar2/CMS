package com.TranEco.cardManagement.accountManagement.services;

import com.TranEco.cardManagement.accountManagement.model.AccountRequest;
import com.TranEco.cardManagement.accountManagement.model.AccountResponse;

public interface AccountService {
	public AccountResponse accountManagement(AccountRequest request);
}
