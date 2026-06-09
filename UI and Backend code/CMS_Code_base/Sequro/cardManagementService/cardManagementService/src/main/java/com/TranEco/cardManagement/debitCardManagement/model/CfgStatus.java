package com.TranEco.cardManagement.debitCardManagement.model;

import lombok.Data;

@Data
public class CfgStatus 
{
	private int id;
	private String participantId;
	private String statusDescription;
	private String statusFlag;
	private String extId;
	private Character tempororyFlag;
	
	
	
}
