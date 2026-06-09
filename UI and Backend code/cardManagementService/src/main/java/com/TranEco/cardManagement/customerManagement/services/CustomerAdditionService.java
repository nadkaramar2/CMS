package com.TranEco.cardManagement.customerManagement.services;

import com.TranEco.cardManagement.customerManagement.model.CustomerAdditionRequest;
import com.TranEco.cardManagement.customerManagement.model.CustomerAdditionResponse;

public interface CustomerAdditionService {
	public CustomerAdditionResponse addCustomer(CustomerAdditionRequest request);
}

