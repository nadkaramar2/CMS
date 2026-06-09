package com.traneco.role.model;

import java.util.Date;

import lombok.Data;

@Data
public class RoleMenuMappingTemp {

	private int id;
	private int roleTempId;
	private int roleMenuMappingId;
	private int participantId;
	private int roleId;
	private String menuId;
	private int status;
	private int createdBy;
	private Date createdDate;
	private int lastModifiedBy;
	private Date lastModifiedDate;
	private int approvalStatusId;
	private String groupId;
	
	
}
