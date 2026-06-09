package com.TranEco.cardManagement.crypt.model;

import java.util.Date;

import lombok.Data;

@Data
public class EncryptKeyModel 
{
	private int id;
	private String keySalt;
	private String keyPhrase;
	private String key;
	private String value;
	private Date createdDate;
	
	private String message;
	private String outResponseCode;
	private String code;
	private String outCard;
	private String flag;
	
	
	
	@Override
	public String toString() {
		return "EncryptKeyModel [id=" + id + ", keySalt=" + keySalt + ", keyPhrase=" + keyPhrase + ", key=" + key
				+ ", value=" + value + ", createdDate=" + createdDate + ", message=" + message + ", outResponseCode="
				+ outResponseCode + ", code=" + code + ", outCard=" + outCard + ", flag=" + flag + "]";
	}
	
	
}
