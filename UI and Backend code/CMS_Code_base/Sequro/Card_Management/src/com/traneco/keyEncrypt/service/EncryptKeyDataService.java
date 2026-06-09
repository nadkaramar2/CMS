package com.traneco.keyEncrypt.service;

import com.traneco.common.ResponseBean;
import com.traneco.keyEncrypt.model.EncryptKeyModel;

public interface EncryptKeyDataService {

	int updateEncDecValueInDB(EncryptKeyModel encryptKeyModel);

	int insertEncDecValueInTempDB(String keyPartForDBFile);
	
	public ResponseBean addGenetaedKey(String value);

	public EncryptKeyModel getEncDecValue();

	public EncryptKeyModel getMaskedValue();

	public EncryptKeyModel getMaskedFileKey();

	public EncryptKeyModel getPlainFileKey();
	
	public EncryptKeyModel getClearFileKey();
	
	EncryptKeyModel getEncDecTempValueFromDB();

	int insertEncDecValueInDB(EncryptKeyModel encryptKeyModel);

	int deleteEncDecTempValueFromDB();

}
