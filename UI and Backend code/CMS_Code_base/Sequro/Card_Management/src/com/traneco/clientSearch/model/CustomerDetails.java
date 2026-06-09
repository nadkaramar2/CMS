package com.traneco.clientSearch.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class CustomerDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strCustomerID;	
	private String strParticipantID;
	private String strCitizenID;
	private String strCIFKey;
	private String strFirstName;
	private String strMiddleName;
	private String strLastName;
	private String strGender;
	private String strDOB;
	private String strMotherMaidenName;
	private String id;
	
	
}
