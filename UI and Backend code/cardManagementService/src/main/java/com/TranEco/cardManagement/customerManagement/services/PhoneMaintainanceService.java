package com.TranEco.cardManagement.customerManagement.services;

import com.TranEco.cardManagement.customerManagement.model.PhoneMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.PhoneMaintainanceResponse;

public interface PhoneMaintainanceService {
	public PhoneMaintainanceResponse maintainPhone(PhoneMaintainanceRequest request);
	
}

