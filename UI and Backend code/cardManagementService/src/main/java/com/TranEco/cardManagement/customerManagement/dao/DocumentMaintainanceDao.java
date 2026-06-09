package com.TranEco.cardManagement.customerManagement.dao;

import com.TranEco.cardManagement.customerManagement.model.DocumentMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.DocumentMaintainanceResponse;

public interface DocumentMaintainanceDao 
{
	public DocumentMaintainanceResponse maintainDocument(DocumentMaintainanceRequest request);
}
