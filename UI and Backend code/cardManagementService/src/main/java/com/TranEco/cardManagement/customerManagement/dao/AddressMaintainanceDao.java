package com.TranEco.cardManagement.customerManagement.dao;

import com.TranEco.cardManagement.customerManagement.model.AddressMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.AddressMaintainanceResponse;

public interface AddressMaintainanceDao 
{
	public AddressMaintainanceResponse maintainAddress(AddressMaintainanceRequest request);
}
