package com.traneco.keyEncrypt.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EncryptKeyModel 
{
	private int id;
	private String key; 
	private String value;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdDate;
	
	private String createdBy;
	
	
	
	private String password;
	private String responseCode;
	private String responseDesc;
	private String message;
	private String flag;
	private String senPwd;
	
	private String custodian1Password;
	private String custodian2Password;
	
	private String custodian1Key;
	private String custodian2Key;
	
	private String userType;
	@Override
	public String toString() 
	{
		return "EncryptKeyModel [id=" + id + ", key=" + key + ", value=" + value + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + ", password=" + password + "]";
	}

	
	
	

	
	
	
}
