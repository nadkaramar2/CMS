package com.TranEco.cardManagement.customerManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.common.Constant;
import com.TranEco.cardManagement.customerManagement.dao.CustomerAdditionDaoImpl;
import com.TranEco.cardManagement.customerManagement.model.CustomerAdditionRequest;
import com.TranEco.cardManagement.customerManagement.model.CustomerAdditionResponse;
import com.TranEco.cardManagement.services.EhCacheServiceImpl;

@Service
public class CustomerAdditionServiceImpl implements CustomerAdditionService{

	@Autowired
	CustomerAdditionDaoImpl customerAddittionDao;
	
	@Autowired
	EhCacheServiceImpl ehCacheService;
	
	@Override
	public CustomerAdditionResponse addCustomer(CustomerAdditionRequest request) 
	{
		CustomerAdditionResponse response = new CustomerAdditionResponse();
		if(validateAddressType(request.getStrParticipantID() + request.getStrAddressType())) {
			response.setOutResponseCode(Constant.CMS_Constants.INVALID_ADDRESS_TYPE);
			response.setMessage("Invalid Address Type");
		}
		else if(validateEmailType(request.getStrParticipantID() + request.getStrEmailType())) {
			response.setOutResponseCode(Constant.CMS_Constants.INVALID_EMAIL_TYPE);
			response.setMessage("Invalid Email Type"); 
		} /*
			 * else if(validatePhoneType(request.getStrParticipantID() +
			 * request.getStrPhoneType())) {
			 * response.setOutResponseCode(Constant.CMS_Constants.INVALID_PHONE_TYPE);
			 * response.setMessage("Invalid Phone Type"); }
			 */else if(validateDocumentType(request.getStrDocumentType())) {
			response.setOutResponseCode(Constant.CMS_Constants.INVALID_DOCUMENT_TYPE);
			response.setMessage("Invalid Document Type"); 
		}
		else 
		{
			response = customerAddittionDao.addCustomer(request);
		}
		return response;
	}

	public boolean validateAddressType(String partID) {
		return ehCacheService.getCfg_Address_Type().get(partID) == null ? true : false;
	}
	
	public boolean validatePhoneType(String partID) {
		return ehCacheService.getCfg_Phone_Type().get(partID) == null ? true : false;
	}
	
	public boolean validateEmailType(String partID) {
		return ehCacheService.getCfg_Email_Type().get(partID) == null ? true : false;
	}
	
	public boolean validateDocumentType(String partID) {
		return ehCacheService.getCfg_Document_Type().get(partID) == null ? true : false;
	}

	}
