package com.TranEco.cardManagement.cryptservice;

import com.TranEco.cardManagement.crypt.model.EncryptKeyModel;

public interface EncryptKeyDataService {

	public EncryptKeyModel getKeySaltPhrase();
	
	public EncryptKeyModel getEncDecValue();
	
	public EncryptKeyModel addGenetaedKey(String value); 
		
}
