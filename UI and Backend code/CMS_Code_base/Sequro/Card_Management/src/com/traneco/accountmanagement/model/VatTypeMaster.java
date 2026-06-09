package com.traneco.accountmanagement.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class VatTypeMaster implements Serializable{

	private static final long serialVersionUID = 1L;
	private String strID;
	private String participantId;
	private String vatType;
	private String vatDescription;
	private String status;
	private Date createdDate;
	private String createdBy;
}
