package com.TranEco.cardManagement.crypt.dao;

import com.TranEco.cardManagement.crypt.model.EncryptKeyModel;

public interface EncryptKeyDataDao 
{
	public EncryptKeyModel getKeySaltPhrase();

	public EncryptKeyModel getEncDecValue();
}
