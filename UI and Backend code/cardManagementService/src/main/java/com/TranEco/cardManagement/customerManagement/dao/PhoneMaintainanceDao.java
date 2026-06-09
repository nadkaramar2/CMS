package com.TranEco.cardManagement.customerManagement.dao;

import com.TranEco.cardManagement.customerManagement.model.PhoneMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.PhoneMaintainanceResponse;

public interface PhoneMaintainanceDao 
{
	public PhoneMaintainanceResponse maintainPhone(PhoneMaintainanceRequest request);
}
