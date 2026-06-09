package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class GLAccountChargeMaster implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String strID;
	private String strChargeType;
	private String strChargeDescripation;
	private String strChargeRelatedDesp;
	private String strChargeRelated;
	private String strCreatedBy;
	private String strCreatedDate;

	

}
