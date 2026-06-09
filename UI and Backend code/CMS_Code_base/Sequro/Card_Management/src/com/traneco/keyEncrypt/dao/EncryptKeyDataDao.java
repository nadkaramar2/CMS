package com.traneco.keyEncrypt.dao;

import com.traneco.keyEncrypt.model.EncryptKeyModel;

public interface EncryptKeyDataDao 
{
	public int updateEncDecValueInDB(EncryptKeyModel encryptKeyModel);

	public int insertEncDecValueInTempDB(String keyPartForDBFile);

	public EncryptKeyModel getEncDecValue();

	public EncryptKeyModel getMaskedValue();

	public EncryptKeyModel getEncDecTempValueFromDB();

	public int insertEncDecValueInDB(EncryptKeyModel encryptKeyModel);

	int deleteEncDecTempValueFromDB();

}
