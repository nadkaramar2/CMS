package com.traneco.configuration.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PreSubAccountMaster implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String strID;	
	private String strMobileNo;	
	private String strCreatedBy;
	private String strAccountType;
	private String strIsCustIdCreated;
	private String strIsAccountNoCreated;
	
	private Date dateOfCreation;
	private String fromDate;
	private String toDate;
	private String strCustId;
	private String strDateOfRegistration;
	private String strDescription;
	private String ageing;
}
