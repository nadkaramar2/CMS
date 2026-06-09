package com.traneco.role.model;

import java.util.Date;

import lombok.Data;

//Created By Prashant 01 April

@Data
public class UserRoleMappingTemp {

	private int id;
	private int userRoleId;
	private int userId;
	private int roleId;
	private int participantId;
	private String userStatus;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;
	private int approvalStatusId;
	private int groupId;
	
	
	
}
