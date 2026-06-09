package com.traneco.service.model;

import java.io.Serializable;

import lombok.Data;


@Data
public class AccountKycDetails implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String strID;	
	private String strParticipantID;	
	private String strAccountType;	
	private String strMobileNo;	
	private String strAccountNumber;	
	private String strAddressProofDocumentType;	
	private String strAddressDocumentValue;	
	private String strIdentityProofDocumentType;	
	private String strIdentityProofDocumentValue;
	private String uploadDocument;
	
	private String strIdentityProofDocumentName;
	private String strAddressDocumentName;
	
	private String strCustId;
	
	private String strBvnNumber;
	private String strTier1PassportPhotograph;
	private String strTier2PassportPhotograph;	
}
