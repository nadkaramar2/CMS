package com.TranEco.cardManagement.crypt.dao;

import java.util.List;

import com.TranEco.cardManagement.crypt.model.EncryptKeyData;

public interface EncryptedKeyDataDao
{

	int saveKeyData(EncryptKeyData encryptKeyData);
	
	List<EncryptKeyData> getKeyDataByFileName(String fileName);
}
