package com.traneco.role.model;

import java.util.Date;

import lombok.Data;

@Data
public class RoleMenuMapping 
{
	private int id;
	private int participantId;
	private int roleId;
	private String menuId;
	private String status;
	private int createdBy;
	private Date createdDate;
	private int lastModifiedBy;
	private Date lastModifiedDate;
	private String groupId;
}
