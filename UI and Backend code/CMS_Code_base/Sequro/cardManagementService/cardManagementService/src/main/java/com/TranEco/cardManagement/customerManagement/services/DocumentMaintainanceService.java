package com.TranEco.cardManagement.customerManagement.services;

import com.TranEco.cardManagement.customerManagement.model.DocumentMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.DocumentMaintainanceResponse;

public interface DocumentMaintainanceService {
	public DocumentMaintainanceResponse maintainDocument(DocumentMaintainanceRequest request);
	
}

