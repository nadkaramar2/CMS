package com.TranEco.cardManagement.customerManagement.dao;

import com.TranEco.cardManagement.customerManagement.model.EmailMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.EmailMaintainanceResponse;

public interface EmailMaintainanceDao 
{
	public EmailMaintainanceResponse maintainEmail(EmailMaintainanceRequest request);
}
