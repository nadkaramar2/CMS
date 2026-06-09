package com.traneco.accountmanagement.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class FeeTypeMaster implements Serializable
{

	private static final long serialVersionUID = 1L;
	private int id;
	private String participantId;
	private String feeType;
	private String feeDescription;
	private String vatType;
	private String status;
	private Date createdDate;
	private String createdBy;
	
}
