package com.TranEco.cardManagement.customerManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.common.Constant;
import com.TranEco.cardManagement.customerManagement.dao.EmailMaintainanceDaoImpl;
import com.TranEco.cardManagement.customerManagement.model.EmailMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.EmailMaintainanceResponse;
import com.TranEco.cardManagement.services.EhCacheServiceImpl;

@Service
public class EmailMaintainanceServiceImpl implements EmailMaintainanceService{

	@Autowired
	EmailMaintainanceDaoImpl EmailMaintainanceDao;
	
	@Autowired
	EhCacheServiceImpl ehCacheService;
	
	@Override
	public EmailMaintainanceResponse maintainEmail(EmailMaintainanceRequest request) {
		if(validateEmailType(request.getStrParticipantID() + request.getStrEmailType())) 
		{
			EmailMaintainanceResponse EmailMaintainanceResponse = new EmailMaintainanceResponse();
			EmailMaintainanceResponse.setOutResponseCode(Constant.CMS_Constants.INVALID_EMAIL_TYPE);
			EmailMaintainanceResponse.setMessage("Invalid Email Type"); ;
					return EmailMaintainanceResponse;
		}
		else {
			return EmailMaintainanceDao.maintainEmail(request);
		}
	}

	public boolean validateEmailType(String partID) {
		return ehCacheService.getCfg_Email_Type().get(partID) == null ? true : false;
	}
	
}
