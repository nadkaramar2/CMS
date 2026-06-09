package com.TranEco.cardManagement.customerManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.common.Constant;
import com.TranEco.cardManagement.customerManagement.dao.PhoneMaintainanceDaoImpl;
import com.TranEco.cardManagement.customerManagement.model.PhoneMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.PhoneMaintainanceResponse;
import com.TranEco.cardManagement.services.EhCacheServiceImpl;

@Service
public class PhoneMaintainanceServiceImpl implements PhoneMaintainanceService{

	@Autowired
	PhoneMaintainanceDaoImpl PhoneMaintainanceDao;
	
	@Autowired
	EhCacheServiceImpl ehCacheService;
	
	@Override
	public PhoneMaintainanceResponse maintainPhone(PhoneMaintainanceRequest request) {
		/*
		 * if(validatePhoneType(request.getStrParticipantID() +
		 * request.getStrPhoneType())) { PhoneMaintainanceResponse
		 * PhoneMaintainanceResponse = new PhoneMaintainanceResponse();
		 * PhoneMaintainanceResponse.setOutResponseCode(Constant.CMS_Constants.
		 * INVALID_PHONE_TYPE);
		 * PhoneMaintainanceResponse.setMessage("Invalid Phone Type"); ; return
		 * PhoneMaintainanceResponse; } else {
		 */
			return PhoneMaintainanceDao.maintainPhone(request);
		/* } */
	}

	public boolean validatePhoneType(String partID) {
		return ehCacheService.getCfg_Phone_Type().get(partID) == null ? true : false;
	}
	
}
