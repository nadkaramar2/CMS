package com.traneco.clientSearch.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class DetailedClientCardRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strParticipantID;
	private String strCustomerID;
	private String strCitizenID;
	private String strCIFKey;
}