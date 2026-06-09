package com.TranEco.cardManagement.customerManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.common.Constant;
import com.TranEco.cardManagement.customerManagement.dao.AddressMaintainanceDaoImpl;
import com.TranEco.cardManagement.customerManagement.model.AddressMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.AddressMaintainanceResponse;
import com.TranEco.cardManagement.services.EhCacheServiceImpl;

@Service
public class AddressMaintainanceServiceImpl implements AddressMaintainanceService{

	@Autowired
	AddressMaintainanceDaoImpl addressMaintainanceDao;
	
	@Autowired
	EhCacheServiceImpl ehCacheService;
	
	@Override
	public AddressMaintainanceResponse maintainAddress(AddressMaintainanceRequest request) {
		if(validateAddressType(request.getStrParticipantID() + request.getStrAddressType())) 
		{
			AddressMaintainanceResponse addressMaintainanceResponse = new AddressMaintainanceResponse();
			addressMaintainanceResponse.setOutResponseCode(Constant.CMS_Constants.INVALID_ADDRESS_TYPE);
			addressMaintainanceResponse.setMessage("Invalid Address Type"); ;
					return addressMaintainanceResponse;
		}
		else {
			return addressMaintainanceDao.maintainAddress(request);
		}
	}

	public boolean validateAddressType(String partID) {
		return ehCacheService.getCfg_Address_Type().get(partID) == null ? true : false;
	}
	
}
