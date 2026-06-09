package com.traneco.configuration.services;

import java.util.List;

import com.traneco.configuration.model.GLAccountLoadingMaster;

//created by ankit 
public interface GLAccountConfigurationService {

	  List<GLAccountLoadingMaster> getGLAccountTypes();

	  int addLoadBalanceInGlAccount(GLAccountLoadingMaster glAccountLoadingMaster);
	
	  String getAvailabelBalance(String accountType,String accountNumber);
}
