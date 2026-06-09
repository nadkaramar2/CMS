package com.TranEco.cardManagement.crypt.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class RsaKeyValue implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String publicKey;
	private String privateKey;

}
