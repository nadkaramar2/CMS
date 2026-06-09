package com.TranEco.cardManagement.crypt.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class EncryptKeyData implements Serializable
{
	private static final long serialVersionUID = 1L;
	public int id;
	public String aesEncodedKey;
	public String aesEncodedIv;
	public String fileName;
	
}
