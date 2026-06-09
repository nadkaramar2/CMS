package com.TranEco.cardManagement.customerManagement.services;

import com.TranEco.cardManagement.customerManagement.model.AddressMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.AddressMaintainanceResponse;

public interface AddressMaintainanceService {
	public AddressMaintainanceResponse maintainAddress(AddressMaintainanceRequest request);
	
}

