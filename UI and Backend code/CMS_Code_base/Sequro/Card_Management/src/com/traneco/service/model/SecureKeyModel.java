package com.traneco.service.model;

import java.util.Date;

import lombok.Data;

@Data
public class SecureKeyModel 
{
	private String generateKey;
	private String createdBy;
	private Date createdDate;
}
