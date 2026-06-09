package com.TranEco.cardManagement.customerManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.common.Constant;
import com.TranEco.cardManagement.customerManagement.dao.DocumentMaintainanceDaoImpl;
import com.TranEco.cardManagement.customerManagement.model.DocumentMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.DocumentMaintainanceResponse;
import com.TranEco.cardManagement.services.EhCacheServiceImpl;

@Service
public class DocumentMaintainanceServiceImpl implements DocumentMaintainanceService{

	@Autowired
	DocumentMaintainanceDaoImpl DocumentMaintainanceDao;
	
	@Autowired
	EhCacheServiceImpl ehCacheService;
	
	@Override
	public DocumentMaintainanceResponse maintainDocument(DocumentMaintainanceRequest request) {
		if(validateDocumentType(request.getStrDocumentType())) 
		{
			DocumentMaintainanceResponse DocumentMaintainanceResponse = new DocumentMaintainanceResponse();
			DocumentMaintainanceResponse.setOutResponseCode(Constant.CMS_Constants.INVALID_DOCUMENT_TYPE);
			DocumentMaintainanceResponse.setMessage("Invalid Document Type"); ;
					return DocumentMaintainanceResponse;
		}
		else {
			return DocumentMaintainanceDao.maintainDocument(request);
		}
	}

	public boolean validateDocumentType(String partID) {
		return ehCacheService.getCfg_Document_Type().get(partID) == null ? true : false;
	}
	
}
