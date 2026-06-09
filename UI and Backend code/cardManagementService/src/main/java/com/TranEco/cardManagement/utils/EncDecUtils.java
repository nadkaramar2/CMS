package com.TranEco.cardManagement.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.TranEco.cardManagement.crypt.model.EncryptKeyModel;
import com.TranEco.cardManagement.cryptservice.EncryptKeyDataService;

@Component
public class EncDecUtils 
{

	@Autowired
	Environment env;
	
	@Autowired
	EncryptKeyDataService encryptKeyDataService;
	
	  public String getSecretKeys() 
	  {			
		  EncryptKeyModel encryptKeyModel = encryptKeyDataService.getKeySaltPhrase();
		  String generateKey = encryptKeyModel.getKey().concat(env.getProperty("key"));
		  return generateKey;
	  }
	 
}
