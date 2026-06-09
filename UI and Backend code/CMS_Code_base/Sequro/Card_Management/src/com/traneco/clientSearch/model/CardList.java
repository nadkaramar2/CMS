package com.traneco.clientSearch.model;
import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CardList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strCardType;
	private String strCardNumber;
	private String strCardSeqNumber;
	private String strEmbossLine1;
	private String strCardIssueDate;
	private String strExpiryDate;
	private String strCardStatus;
	private String strCardIssuedUser;
	private String strDescription;
	private String strMaskCardNumber;
	
}
