package com.traneco.configuration.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class CardPlasticModel {
	private String strSelectID;
	private String strID;
	private String strCardType;
	private String strVendorID;
	private String strCVV;
	private String strServicePos;
	private String strExpPos;
	private String strSeqPos;
	private String typeDesc;
	private String participantId;
	private String pinMailerFlag;
	private Date pinMailerIssueDate;
	
	private String strCardNumber;
}
