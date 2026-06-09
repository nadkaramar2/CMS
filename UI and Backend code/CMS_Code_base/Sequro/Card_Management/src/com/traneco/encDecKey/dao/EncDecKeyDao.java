package com.traneco.encDecKey.dao;

import com.traneco.keyEncrypt.model.EncryptKeyModel;

public interface EncDecKeyDao {

	int validateCustdian1Password(EncryptKeyModel encryptKeyModel);

	int validateCustdian2Password(EncryptKeyModel encryptKeyModel);
}
