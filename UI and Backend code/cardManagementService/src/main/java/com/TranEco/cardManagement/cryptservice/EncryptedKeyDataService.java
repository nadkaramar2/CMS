package com.TranEco.cardManagement.cryptservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.crypt.dao.EncryptedKeyDataDao;
import com.TranEco.cardManagement.crypt.model.EncryptKeyData;

@Service
public class EncryptedKeyDataService
{
	@Autowired
	EncryptedKeyDataDao encryptedKeyDataDao;

	public int saveData(EncryptKeyData encryptKeyData)
	{
		int saveKeyData = encryptedKeyDataDao.saveKeyData(encryptKeyData);
		return saveKeyData;
	}
	
}
