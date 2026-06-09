package com.TranEco.cardManagement.debitCardManagement.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DebitCardIssuanceResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String outResponseCode;
	private String message;
	private String outCard;
	private String outTokenCard;
	
	private String encryptedCardNumber;
}
