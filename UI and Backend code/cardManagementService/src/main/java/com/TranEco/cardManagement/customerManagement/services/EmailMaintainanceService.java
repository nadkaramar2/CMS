package com.TranEco.cardManagement.customerManagement.services;

import com.TranEco.cardManagement.customerManagement.model.EmailMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.EmailMaintainanceResponse;

public interface EmailMaintainanceService {
	public EmailMaintainanceResponse maintainEmail(EmailMaintainanceRequest request);
	
}

