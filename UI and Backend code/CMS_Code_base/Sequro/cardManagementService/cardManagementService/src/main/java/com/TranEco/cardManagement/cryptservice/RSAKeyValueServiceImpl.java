package com.TranEco.cardManagement.cryptservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.crypt.dao.RSAKeyValueDao;
import com.TranEco.cardManagement.crypt.model.RsaKeyValue;

@Service
public class RSAKeyValueServiceImpl implements RSAKeyValueService
{
	@Autowired
	RSAKeyValueDao RSAKeyValueDao;
	
	@Override
	public RsaKeyValue getRSAKeyValue(int id) 
	{
		return RSAKeyValueDao.getRSAKeyValue(id);
	}

}
