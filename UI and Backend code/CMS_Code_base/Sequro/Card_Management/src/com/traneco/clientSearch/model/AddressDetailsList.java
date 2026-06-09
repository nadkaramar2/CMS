package com.traneco.clientSearch.model;
import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AddressDetailsList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strAddressID;
	private String strAddressType;
	private String strAddressTypeDesc;
	private String strAddress1;
	private String strAddress2;
	private String strAddress3;
	private String strCity;
	private String strState;
	private String strCountryCode;
	private String strPinCode;
	private String strAddressPrimaryFlag;
	private String strCIFKey;
	private String strCitizenID;
	private String strCustomerID;
	private String strFunctionType;
	private String strParticipantID;
	private String strRequestData;
	private String strAddFlag;
}
