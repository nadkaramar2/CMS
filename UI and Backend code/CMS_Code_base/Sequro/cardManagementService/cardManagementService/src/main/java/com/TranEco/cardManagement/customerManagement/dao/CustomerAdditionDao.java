package com.TranEco.cardManagement.customerManagement.dao;

import com.TranEco.cardManagement.customerManagement.model.CustomerAdditionRequest;
import com.TranEco.cardManagement.customerManagement.model.CustomerAdditionResponse;

public interface CustomerAdditionDao 
{
	public CustomerAdditionResponse addCustomer(CustomerAdditionRequest request);
}
