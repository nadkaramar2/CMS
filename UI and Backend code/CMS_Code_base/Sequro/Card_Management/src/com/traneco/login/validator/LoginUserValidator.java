/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used validate login user request parameters.
 * 
 *@History
 *=============================================================================================================================================
 *    @Version         @Date         		@Author                     @Purpose	
 *=============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used validate login user request parameters.
 *=============================================================================================================================================
 *
 */

package com.traneco.login.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.traneco.login.model.LoginUser;

@Component
public class LoginUserValidator implements Validator {
	
	@Autowired
	Environment env;
	
	@Override
	public boolean supports(Class<?> classVal) {		
		return LoginUser.class.isAssignableFrom(classVal);
	}

	@Override
	public void validate(Object target, Errors error) {		
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "username", "error.username", env.getProperty("imps.error.username"));
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "password", "error.password", env.getProperty("imps.error.password"));		
	}
}
