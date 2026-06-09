package com.traneco.encDecKey.service;

import com.traneco.common.ResponseBean;
import com.traneco.keyEncrypt.model.EncryptKeyModel;

public interface EncDecKeyService {

	ResponseBean generateCardEncDecKey();

	int validateCustdian1Password(EncryptKeyModel encryptKeyModel);
	
	int validateCustdian2Password(EncryptKeyModel encryptKeyModel);

}
