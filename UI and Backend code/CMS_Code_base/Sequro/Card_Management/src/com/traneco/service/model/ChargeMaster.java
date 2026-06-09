package com.traneco.service.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ChargeMaster implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String strId;
	private String strParticipantID;
	private String strAccountType;
	private String strChargeType;
	private String strChargeDescription;
	private String strChargeRelated;
	private String strChargeRelatedDescription;
	private String strCreatedBy;
	private String strCreatedDate;
}
