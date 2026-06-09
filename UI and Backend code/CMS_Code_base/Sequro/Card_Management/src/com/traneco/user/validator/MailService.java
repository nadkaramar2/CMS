package com.traneco.user.validator;

import com.traneco.user.model.UserBean;

public interface MailService {

	//String mailer(String mailto, String bodyMessage, String subject);
	
	String sendEmailCustomer(UserBean userBean);
}
