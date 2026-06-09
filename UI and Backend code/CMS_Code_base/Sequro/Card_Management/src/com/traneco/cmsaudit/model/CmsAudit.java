package com.traneco.cmsaudit.model;

import java.util.Date;

import lombok.Data;

@Data
public class CmsAudit {

	private String id;
	private String participantId;
	private String tableName;
	private String columnName;
	private String newFeild;
	private String oldFeild;
	private String dmlAction;
	private Date createdDate;
	private String createdBy;
	private String approveBy;
	
	private String status;
}
